# QueryDSL 강의

page 쿼리 조인많으면 성능상문제?


@PersistenceUnit <br>
EntityManagerFactory emf<br>
emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam()) -> 페치 조인시 이미 조인됬는지 확인할때 사용

영속성 컨텍스트가 우선되기 때문에 벌크 연산시 그냥 영속성 컨텍스트 초기화하고 다시 받아오는게 안전
참고 : https://dev-coco.tistory.com/169
