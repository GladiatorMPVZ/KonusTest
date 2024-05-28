### Запуск:
  Поднять контейнеры:
```console
docker-compose up -d --build
```

1. Книга (Book)
ID: Уникальный идентификатор книги (Long)
Title: Название книги (String)
ISBN: Международный стандартный книжный номер (String)
PublicationYear: Год публикации (Integer)
Genre: Жанр книги (String)
Pages: Количество страниц (Integer)
AuthorID: Уникальный идентификатор автора книги (Integer, Foreign Key)

2. Автор (Author)
ID: Уникальный идентификатор автора (Long)
FirstName: Имя автора (String)
LastName: Фамилия автора (String)
BirthYear: Год рождения автора (Integer)
DeathYear: Год смерти автора (Integer, может быть null, если автор жив)

3. Жанр (Genre)
ID: Уникальный идентификатор жанра (Long)
Title: Название жанра (String)

Связи:
Автор - Книга (Связь один-ко-многим)
Жанр - Книга (Связь один-ко-многим)

Проектирование модели данных - 30 минут 
Реализация - 2.5 часа
