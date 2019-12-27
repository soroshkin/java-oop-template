package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook schoolBook) {
        SchoolBook[] temp = schoolBooks;
        schoolBooks = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(temp, 0, schoolBooks, 0, temp.length);
        schoolBooks[schoolBooks.length - 1] = schoolBook;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] schoolBooksFound = new SchoolBook[]{};
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                SchoolBook[] temp = schoolBooksFound;
                schoolBooksFound = new SchoolBook[schoolBooksFound.length + 1];
                System.arraycopy(temp, 0, schoolBooksFound, 0, temp.length);
                schoolBooksFound[schoolBooksFound.length - 1] = schoolBook;
            }
        }
        return schoolBooksFound;
    }

    @Override
    public boolean removeByName(String name) {
        int numberOfBooksToDelete = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                numberOfBooksToDelete++;
            }
        }
        if (numberOfBooksToDelete > 0) {
            SchoolBook[] temp = new SchoolBook[schoolBooks.length - numberOfBooksToDelete];
            for (int i = 0, j = 0; i < temp.length && j < schoolBooks.length; j++) {
                if (!schoolBooks[i].getName().equals(name)) {
                    temp[i] = schoolBooks[j];
                    i++;
                }
            }
            schoolBooks = temp;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
