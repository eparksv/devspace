package our.portfolio.devspace.configuration.jpa;

import org.hibernate.dialect.MySQL8Dialect;

public class ImprovedMySQL8Dialect extends MySQL8Dialect {

    /**
     * 테이블이 없을 때 "ALTER TABLE"을 실행하여 발생하는 예외를 수정하기 위해 SQL "ALTER TABLE"의 뒤에 "IF EXISTS"를 추가한다.
     */
    @Override
    public boolean supportsIfExistsAfterAlterTable() {
        return true;
    }
}
