package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] authorsOld = authors;
            authors = new Author[authors.length + 1];
            System.arraycopy(authorsOld, 0, authors, 0, authorsOld.length);
            authors[authors.length - 1] = author;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        boolean isAuthorFound = false;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                isAuthorFound = true;
                continue;
            }
            if (isAuthorFound) {
                authors[i - 1] = authors[i];
            }
        }
        Author[] temp = authors;
        authors = new Author[authors.length - 1];
        System.arraycopy(temp, 0, authors, 0, authors.length);
        return isAuthorFound;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
