package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L; // Key 값을 형성해주는 친구

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // sequence로 key를 하나씩 올리면서 저장
        store.put(member.getId(), member); // HashMap의 put 메소드로 Map에 key와 member를 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null일때도 Optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // Java의 Stream과 람다식을 이용해서, 회원의 이름에 맞는 Member를 찾을때 까지 반복
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // ArrayList의 생성자의 매개변수로 Map의 모든 Member를 준다
    }

    public void clearStore(){
        store.clear();
    }
}
