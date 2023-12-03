package dao;

import dto.MemberDTO;

import java.util.ArrayList;

public class MemberDAO implements DAO<MemberDTO, String>{
    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        return null;
    }

    @Override
    public ArrayList<MemberDTO> findById(String s) {
        return null;
    }

    @Override
    public ArrayList<MemberDTO> findAll() {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
