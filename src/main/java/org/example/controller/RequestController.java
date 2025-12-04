package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.service.RequestService;
import org.example.util.MobileUtils;
import org.example.vo.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/usr/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    // GET 매핑 - 폼 페이지 보여주기 (PC/모바일 자동 분기)
    @GetMapping
    public String showRequestForm(HttpServletRequest req) {
        return MobileUtils.view("request", req);
    }

    // POST 매핑 - 요청 제출
    @PostMapping("/submit")
    public String submitRequest(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam(required = false) List<String> scope,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String referenceUrl,
            @RequestParam(required = false) String details,
            @RequestParam(value = "guide", required = false) MultipartFile guideFile,
            @RequestParam(value = "files", required = false) MultipartFile[] midiFiles,
            @RequestParam(value = "reference", required = false) MultipartFile referenceFile,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req
    ) throws IOException {

        RequestVo request = new RequestVo();
        request.setName(name);
        request.setContact(phone);
        request.setEmail(email);
        request.setWorkScope(scope != null ? String.join(", ", scope) : "");
        request.setDeadline(deadline);
        request.setGenre(genre);
        request.setReferenceLink(referenceUrl);
        request.setNote(details);

        // 파일 저장
        if (guideFile != null && !guideFile.isEmpty()) {
            request.setGuideFile(guideFile.getBytes());
            request.setGuideFilename(guideFile.getOriginalFilename());
        }

        if (midiFiles != null && midiFiles.length > 0 && !midiFiles[0].isEmpty()) {
            int totalLength = 0;
            for (MultipartFile f : midiFiles) if (!f.isEmpty()) totalLength += f.getBytes().length;
            byte[] allBytes = new byte[totalLength];
            int pos = 0;
            for (MultipartFile f : midiFiles) {
                if (!f.isEmpty()) {
                    byte[] b = f.getBytes();
                    System.arraycopy(b, 0, allBytes, pos, b.length);
                    pos += b.length;
                }
            }
            request.setMidiFile(allBytes);
            request.setMidiFilename(midiFiles[0].getOriginalFilename());
        }

        if (referenceFile != null && !referenceFile.isEmpty()) {
            request.setReferenceFile(referenceFile.getBytes());
            request.setReferenceFilename(referenceFile.getOriginalFilename());
        }

        requestService.saveRequest(request);
        redirectAttributes.addFlashAttribute("message", "제출이 완료되었습니다!");

        // Host 기반으로 모바일/PC 리다이렉트
        return MobileUtils.isMobile(req) ? "redirect:/usr/request" : "redirect:/usr/request";
    }

    @Controller
    @RequestMapping("/admin/requests")
    public class AdminController {

        @Autowired
        private RequestService requestService;

        @GetMapping
        public String listRequests(Model model) {
            List<RequestVo> requests = requestService.getAllRequests();
            model.addAttribute("requests", requests);
            return "admin/request-list";
        }

        @GetMapping("/download/{id}/{fileType}")
        public ResponseEntity<Resource> downloadFile(
                @PathVariable Long id,
                @PathVariable String fileType
        ) throws UnsupportedEncodingException {
            RequestVo request = requestService.getRequestById(id);
            if (request == null) return ResponseEntity.notFound().build();

            byte[] fileData;
            String filename;

            switch (fileType) {
                case "guide":
                    fileData = request.getGuideFile();
                    filename = request.getGuideFilename();
                    break;
                case "midi":
                    fileData = request.getMidiFile();
                    filename = request.getMidiFilename();
                    break;
                case "reference":
                    fileData = request.getReferenceFile();
                    filename = request.getReferenceFilename();
                    break;
                default:
                    return ResponseEntity.badRequest().build();
            }

            if (fileData == null || fileData.length == 0 || filename == null) return ResponseEntity.notFound().build();

            String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
            ByteArrayResource resource = new ByteArrayResource(fileData);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFilename)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(fileData.length)
                    .body(resource);
        }
    }
}
