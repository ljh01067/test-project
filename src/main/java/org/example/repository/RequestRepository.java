package org.example.repository;

import org.apache.ibatis.annotations.*;
import org.example.vo.RequestVo;
import java.util.List;

@Mapper
public interface RequestRepository {

    @Insert("INSERT INTO request (" +
            "name, contact, email, work_scope, deadline, genre, reference_link, note, " +
            "guide_file, midi_file, reference_file, " +
            "guide_filename, midi_filename, reference_filename, created_at" +
            ") VALUES (" +
            "#{name}, #{contact}, #{email}, #{workScope}, #{deadline}, #{genre}, #{referenceLink}, #{note}, " +
            "#{guideFile}, #{midiFile}, #{referenceFile}, " +
            "#{guideFilename}, #{midiFilename}, #{referenceFilename}, NOW()" +
            ")")
    void addRequest(RequestVo request);

    @Select("SELECT * FROM request ORDER BY created_at DESC")
    List<RequestVo> findAll();

    @Select("SELECT * FROM request WHERE id = #{id}")
    RequestVo findById(Long id);
}
