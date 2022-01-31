package hello.hellospringnoref.repository;

import hello.hellospringnoref.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //memoryDB
    private final static Map<Long, Member> memoryDB = new HashMap<>();
    private static Long id = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++id);
        memoryDB.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memoryDB.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return memoryDB.values()
                .stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memoryDB.values());
    }

    public void deleteAll(){
        memoryDB.clear();
    }
}
