package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    public BoardRepository boardRepository;
    public void write(Board board, MultipartFile file) throws Exception{ //글 작성
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID(); // 랜덤ID
        String fileName = uuid + "_" + file.getOriginalFilename(); //식별자 + _ + 원래파일이름

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);
        boardRepository.save(board);
    }

    public Page<Board> boardList(Pageable pageable) { //게시글 리스트 처리
        return boardRepository.findAll(pageable);
    } //게시글 리스트 처리

    public Board boardView(Integer id){ //특정 게시글 불러오기
        return boardRepository.findById(id).get();
    } //게시글불러오기

    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

}
