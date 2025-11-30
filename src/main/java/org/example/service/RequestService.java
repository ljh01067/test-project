package org.example.service;

import org.example.repository.RequestRepository;
import org.example.vo.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public void saveRequest(RequestVo request) {
        requestRepository.addRequest(request);
    }

    // 전체 조회 추가
    public List<RequestVo> getAllRequests() {
        return requestRepository.findAll();
    }

    // ID로 조회 추가
    public RequestVo getRequestById(Long id) {
        return requestRepository.findById(id);
    }
}
