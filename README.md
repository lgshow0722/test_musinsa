# 무신사 과제
기간 : 2025.03.10 ~ 2025.03.17

지원자 : 이진욱

이메일 : lgshow0722@gmail.com

** 본 과제는 "과제 이해 및 설계 범위 확정" > "개략적 설계" > "상세 설계" 순으로 내용을 구체화합니다.

* 과제 이해 및 설계 범위 확정
  * 해당 단계는 개략적으로 프로젝트에 대한 구상을 하고 프로젝트 구조에 대한 설계를 합니다.
    *  디렉토리 구조
      * api : 컨트롤러 및 공통 response 관련
      * service : 서비스 레이어
      * repository : jpa, entity 설정
      * dto : json request/response 객체
      * event : 별도 이벤트 수신
      * config : 스웨거 설정
    * DB
      * H2 Embedded DB를 활용
      * 해당 과제에는 3개의 테이블을 활용하고자 한다.
      * 카테고리 / 브랜드 / 상품
        * 단일 테이블로 구현 가능하지만, 실제 환경을 추측했을 경우 카테고리 / 브랜드가 별도 관리될 필요가 있어 분리하여 설계함
      * 초기 데이터의 경우 schema.sql/data.sql을 통해 프로젝트 구동 시 자동 등록되도록 한다.
    * Entity
      * 카테고리 엔티티는 별도 관리를 함을 전제하여, 브랜드 / 상품 엔티티의 수정/삭제 시 해당 엔티티는 변경되지 않는다.
      * 브랜드 엔티티가 삭제될 경우 하위 상품도 같이 삭제되도록 한다.
      * 상품 엔티티는 등록하고자 하는 브랜드가 브랜드 엔티티에 등록되지 않은 경우 등록이 불가하다.
    * 테스트
      * 본 과제의 유닛테스트는 서비스 단위로 유닛테스트를 구현한다.
      * 단, 초기 데이터 DB 연결 유무에 대한 테스트는 DataJpaTest 어노테이션을 활용하여 구현한다.
        
* 개략적 설계
  * 해당 단계는 설계 범위에 대한 내용을 구현하고 구현 시 발생하는 이슈에 대해 대응합니다.
  * 응답용 공통 클래스를 생성한다.
    * 클래스명 : ApiResponse (api > response > ApiResponse)
    * status / message / data로 구성한다.
      * status : HttpStatus 코드를 리턴
      * message : 출력 메세지
      * data : 제너릭 형태로 리턴되는 데이터를 대응함
  * API 성공/실패에 대한 공통 유틸을 생성한다.
    * 클래스명 : ResponseUtil (api > response > ResponseUtil)
    * 케이스 : 성공 / 성공+데이터 / 성공+메시지 / 실패
  * 공통 Exception Handler 유틸을 생성한다.
    * 클래스명 : ExceptionHandlerUtil (api > response > ExceptionHandlerUtil > wrapWithExceptionHandling)
    * 서비스 내에서 wrapWithExceptionHandling를 래핑하여 에러핸들링에 대해 추상화함.
    * 본 과제에서는 400 / 404 / 500 에러를 대응한다.
      * 400 : Bad_Request / 밸리데이션에 의한 오류
      * 404 : Not_Found / 페이지 없음
      * 500 : Internal_Server_Error / 시스템 오류
  * 헬스 체크 API 생성
    * API 명 : /health (Request Type : GET)
  * application.yml 세팅
    * JPA 설정과 H2 데이터소스 설정
    * 포트 : 8080 (기본 포트)

* 상세 설계
  * 해당 단계는 각 API별 규격 및 표준 응답을 생성합니다.
  * 서비스를 추상화하여 로직을 간소화하여 책임을 분리합니다. 
    * 조회용 추상클래스 : api > service > AbstractReadService
    * 쓰기용 추상클래스 : api > service > AbstractWriteService
  * 추가/수정/삭제 API의 경우 Spring Modulith 개념을 사용하여 확장 가능한 구조를 예측하여 대비함
    * 메인 로직은 동기 이벤트로 처리하고, 이벤트 기반의 부가 기능들은 비동기 이벤트로 처리되도록 설계함
    * 롤백이 될 경우를 감안해 TransactionalEventListener 어노테이션을 사용하고, 트랜잭션 종료 후에 이벤트를 처리하도록 유도함
  * 테스트 코드 설명
    * 조회 관련 테스트 코드
      * CategoryRepositoryTest : DB 연결에 대한 테스트 코드
      * ProjectServiceTest : 조회 내용에 대한 테스트 코드
    * 등록/수정/삭제 관련 테스트 코드
      * BrandTransactionTest : 브랜드 등록/수정/삭제에 대한 테스트 코드
      * MerchandiseTransactionTest : 상품 등록/수정/삭제에 대한 테스트 코드
  * API 명세 (기본주소 : /project)
    * 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
      * API : /cate/lowest-price
      * Request Type : GET
      * Parameter : X
      * Return : Question1Dto
      * 설명
        * 카테고리 별로 최저가를 조회하기 위해 subQuery가 포함된 nativeQuery를 사용함. 전체 데이터를 가져오거나, group by를 할 경우, 성능 상 안좋을 수 있음
        * 카테고리 내 여러 브랜드의 최저가가 같을 경우 최근에 등록된 상품을 출력함(id desc)
    * 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
      * API : /brand/lowest-price
      * Request Type : GET
      * Parameter : X
      * Return : Question2Dto
      * 설명
        * 특정 브랜드가 특정 카테고리 상품이 없을 가능성을 고려하여, 브랜드+카테고리+최저가상품으로 그룹핑한 결과를 카테고리 전체 갯수와 비교하여 싱크 확인 후 모든 카테고리에 상품이 존재하는 브랜드만 만족처리함
        * 데이터의 중복 조회를 방지하고 최초 DB 정보를 재활용하여 cost를 감소하고자 함
    * 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
      * API : /cate/brand-price
      * Request Type : GET
      * Parameter : categoryName (String)
      * Return : Question3Dto
      * 설명
        * JPA의 기본 메서드 중 findTop을 활용하여 카테고리별 최저가/최고가 상품을 각각 조회함
    * 브랜드 추가 API
      * API : /brand/add
      * Request Type : POST
      * Parameter : brandName (String)
      * Return : 성공 or 실패 (ResponseUtil.success() or ResponseUtil.error())
      * 설명
        * 중복된 브랜드명의 경우 등록할 수 없음
    * 브랜드 수정 API
      * API : /brand/mod
      * Request Type : PUT
      * Parameter : BrandUpdateDto (DTO)
      * Return : 성공 or 실패 (ResponseUtil.success() or ResponseUtil.error())
      * 설명
        * 등록되지 않은 ID로 수정할 수 없음
        * 중복된 브랜드명의 경우 등록할 수 없음
    * 브랜드 삭제 API
      * API : /brand/mel
      * Request Type : DELETE
      * Parameter : id (Integer)
      * Return : 성공 or 실패 (ResponseUtil.success() or ResponseUtil.error())
      * 설명
        * 브랜드를 삭제할 경우 하위 엔티티의 상품까지 같이 삭제처리를 한다. (CascadeType.REMOVE)
    * 상품 추가 API
      * API : /merchandise/add
      * Request Type : POST
      * Parameter : MerchandiseDto (DTO)
      * Return : 성공 or 실패 (ResponseUtil.success() or ResponseUtil.error())
      * 설명
        * 카테고리ID / 브랜드ID 가 존재하지 않을 경우 등록이 불가함
    * 상품 수정 API
      * API : /merchandise/mod
      * Request Type : PUT
      * Parameter : MerchandiseUpdateDto (DTO)
      * Return : 성공 or 실패 (ResponseUtil.success() or ResponseUtil.error())
      * 설명
        * 등록되지 않은 ID로 수정할 수 없음
        * 카테고리ID / 브랜드ID 가 존재하지 않을 경우 등록이 불가함
    * 상품 삭제 API
      * API : /merchandise/mel
      * Request Type : DELETE
      * Parameter : id (Integer)
      * Return : 성공 or 실패 (ResponseUtil.success() or ResponseUtil.error())
      * 설명
        * 브랜드 및 카테고리 엔티티에는 영향을 주지 않음
  * 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API : 이 구문은 단일 API로 임의 병합하는 것보다 명시적으로 API를 구분하는 것이 유리하다고 판단하여 6개의 API로 해석함.
  * 출력되는 Dto의 네이밍은 크게 의미를 부여하지 않음

* 추가 고려 사항
  * 각 API가 작동하는 프론트엔드 페이지 작성
    * 단일 프로젝트 임을 감안하여, Thymeleaf 라이브러리 고려
    * -> 프론트엔드 페이지의 역할을 Swagger로 대체함