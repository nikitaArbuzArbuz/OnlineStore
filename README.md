## Интернет магазин “МоиТовары”

### Стек технологий
- Java 17
- Spring Boot 3.4.0
- Spring Data JPA
- PostgreSQL
- docker-compose

### Для запуска
Клонируйте репозиторий
```
git clone https://github.com/nikitaArbuzArbuz/OnlineStore.git
cd OnlineStore
```
В консоли проекта пропишите команду для запуска docker-compose
```
docker-compose up
```
Приложение развернётся в докере вместе с бд.

### Инструкция по использованию API
#### Продукты
Получение всех продуктов
```
GET http://localhost:8080/api/products
```
Получение продукта по id
```
GET http://localhost:8080/api/products/1
```
Создание нового продукта
```
POST http://localhost:8080/api/products
{
  "name": "name",
  "description": "",
  "price": 0,
  "inStock": false
}
```
Изменение существующего продукта
```
PATCH http://localhost:8080/api/products/1
{
  "name": "name",
  "description": "description",
  "inStock": true
}
```
Удаление продукта
```
DELETE http://localhost:8080/api/products/1
```
Получение списка продуктов в отсортированном и фильтрованном виде
```
GET http://localhost:8080/api/products/filter?name=&minPrice=&maxPrice=&inStock=&sortBy=&direction=&limit=
```

#### Поставка
Создание
```
POST http://localhost:8080/api/supplies
{
  "id": 1,
  "documentName": "name",
  "productId": 1,
  "quantitySupplied": 10
}
```
Получение по id
```
GET http://localhost:8080/api/supplies/1
```
Обновление
```
PATCH http://localhost:8080/api/supplies/1
{
  "id": 1,
  "documentName": "name",
  "productId": 2,
  "quantitySupplied": 10
}
```
Удаление
```
DELETE http://localhost:8080/api/supplies/1
```

#### Продажа
Создание
```
POST http://localhost:8080/api/sales
{
  "id": 1,
  "documentName": "name",
  "productId": 1,
  "quantitySold": 0,
  "purchaseCost": 0.0
}
```
Получение по id
```
GET http://localhost:8080/api/sales/1
```
Обновление
```
PATCH http://localhost:8080/api/sales/1
{
  "id": 1,
  "documentName": "name",
  "productId": 1,
  "quantitySold": 10,
  "purchaseCost": 1.0
}
```
Удаление
```
DELETE http://localhost:8080/api/sales/1
```