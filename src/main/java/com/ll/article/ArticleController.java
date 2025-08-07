package com.ll.article;

import com.ll.Rq;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
	private final Scanner sc;
	private final ArticleService articleService;

	public ArticleController(Scanner sc, ArticleService articleService) {
		this.sc = sc;
		this.articleService = articleService;
	}

	public void write() {
		System.out.print("제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("내용 : ");
		String content = sc.nextLine().trim();

		Article article = articleService.write(title, content);

		System.out.printf("%d번 게시물이 등록되었습니다.\n", article.getId());
	}

	public void list() {
		List<Article> articles = articleService.findAll();

		System.out.println("번호 / 제목 / 등록일");
		System.out.println("-------------------");
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			System.out.printf("%d / %s / %s\n", article.getId(), article.getTitle(), article.getRegDate().toLocalDate());
		}
	}

	public void detail(Rq rq) {
		int id = rq.getActionId();

		if (id == -1) {
			System.out.println("게시물 번호를 올바르게 입력해주세요.");
			return;
		}

		Article article = articleService.findById(id);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		System.out.printf("번호 : %d\n", article.getId());
		System.out.printf("제목 : %s\n", article.getTitle());
		System.out.printf("내용 : %s\n", article.getContent());
		System.out.printf("등록일 : %s\n", article.getRegDate().toLocalDate());
	}

	public void update(Rq rq) {
		int id = rq.getActionId();

		if (id == -1) {
			System.out.println("게시물 번호를 올바르게 입력해주세요.");
			return;
		}

		Article article = articleService.findById(id);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		System.out.printf("기존 제목 : %s\n", article.getTitle());
		System.out.print("새 제목 : ");
		String newTitle = sc.nextLine().trim();

		System.out.printf("기존 내용 : %s\n", article.getContent());
		System.out.print("새 내용 : ");
		String newContent = sc.nextLine().trim();

		articleService.update(id, newTitle, newContent);

		System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
	}

	public void delete(Rq rq) {
		int id = rq.getActionId();

		if (id == -1) {
			System.out.println("게시물 번호를 올바르게 입력해주세요.");
			return;
		}

		Article article = articleService.findById(id);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		articleService.delete(id);

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
	}
}
