package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
    @DisplayName("exit 테스트")
    public void testExit() {
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        TestUtil.genScanner("exit");

        new App().run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        String expected = "== 명언 앱 ==" + System.lineSeparator() + "명령) ";
        assertTrue(rs.startsWith(expected));
    }

    @Test
    @DisplayName("write 테스트")
    public void testWrite() {
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        TestUtil.genScanner("""
                write
                나의 죽음을 적들에게 알리지 말라
                이순신
                exit
                """);

        new App().run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("1번 게시물이 등록되었습니다."));
    }

    @Test
    @DisplayName("list 테스트")
    public void testList() {
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        TestUtil.genScanner("""
                write
                제목1
                내용1
                write
                제목2
                내용2
                list
                exit
                """);

        new App().run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("번호 / 제목 / 등록일"));
        assertTrue(rs.contains("-------------------"));
        assertTrue(rs.contains("2 / 제목2 /"));
        assertTrue(rs.contains("1 / 제목1 /"));
    }

    @Test
    @DisplayName("detail 테스트")
    public void testDetail() {
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        TestUtil.genScanner("""
                write
                제목1
                내용1
                detail 1
                detail 2
                exit
                """);

        new App().run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("번호 : 1"));
        assertTrue(rs.contains("등록일 : " + LocalDate.now()));
        assertTrue(rs.contains("제목 : 제목1"));
        assertTrue(rs.contains("내용 : 내용1"));
        assertTrue(rs.contains("2번 게시물은 존재하지 않습니다."));
    }

    @Test
    @DisplayName("update 테스트")
    public void testUpdate() {
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        TestUtil.genScanner("""
                write
                제목
                내용
                update 1
                새 제목
                새 내용
                detail 1
                exit
                """);

        new App().run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("1번 게시물이 수정되었습니다."));
        assertTrue(rs.contains("제목 : 새 제목"));
        assertTrue(rs.contains("내용 : 새 내용"));
    }

    @Test
    @DisplayName("delete 테스트")
    public void testDelete() {
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        TestUtil.genScanner("""
                write
                제목
                내용
                delete 1
                list
                exit
                """);

        new App().run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        assertTrue(rs.contains("1번 게시물이 삭제되었습니다."));
        assertFalse(rs.contains("1 / 제목 /"));
    }
}
