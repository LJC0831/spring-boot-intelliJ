package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    public BoardRepository boardRepository;
    public void write(Board board){ //글 작성

        boardRepository.save(board);
    }

    public List<Board> boardList() { //게시글 리스트 처리
        return boardRepository.findAll();
    } //게시글 리스트 처리

    public Board boardView(Integer id){ //특정 게시글 불러오기
        return boardRepository.findById(id).get();
    } //게시글불러오기

    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

}
