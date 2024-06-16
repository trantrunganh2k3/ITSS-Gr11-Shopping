drop database if exists defridge;
create database defridge;
\c defridge

CREATE TABLE "user" (
  "username" varchar(15) PRIMARY KEY,
  "password" varchar(15),
  "name" varchar(30),
  "gender" varchar(5),
  "email" varchar(30),
  "address" varchar(100),
  "groupID" int,
  "status" varchar(10)
);

CREATE TABLE "Group" (
  "groupID" int PRIMARY KEY,
  "groupName" varchar(15),
  "leaderUN" varchar(15),
  "createDate" date
);

CREATE TABLE "ShoppingList" (
  "listID" int PRIMARY KEY,
  "listName" varchar(30),
  "totalCost" int DEFAULT 0,
  "date" date
);

CREATE TABLE "OwnerShip" (
  "listID" int,
  "username" varchar(15),
  "isOwner" bool
);

CREATE TABLE "ShoppingItems" (
  "itemID" BIGSERIAL PRIMARY KEY,
  "itemName" varchar(30),
  "quantity" decimal,
  "sub_cost" int,
  "boughtBy" varchar(15),
  "listID" int,
  "category" varchar(15),
  "unit" varchar(5),
  "purchaseDay" date
);

CREATE TABLE "FavoriteRecipe" (
  "recipeID" BIGSERIAL PRIMARY KEY,
  "recipeName" varchar(30),
  "description" text,
  "ingredient" text,
  "username" varchar(15)
);

CREATE TABLE "Fridge" (
  "fridgeID" int PRIMARY KEY,
  "groupID" int,
  "username" varchar(15)
);

CREATE TABLE "Ingredient" (
  "ingredientID" BIGSERIAL PRIMARY KEY,
  "name" varchar(15),
  "category" varchar(10),
  "quantity" decimal,
  "unit" varchar(5),
  "purchaseDay" date,
  "expiryDay" date,
  "fridgeID" int
);

CREATE TABLE "Dish" (
  "dishID" int PRIMARY KEY,
  "name" varchar(15),
  "meal" varchar(10),
  "forDate" date,
  "username" varchar(15),
  "fridgeID" int
);

CREATE TABLE "DishIngredient" (
  "dishID" BIGSERIAL,
  "ingredientName" varchar(15),
  "quantity" decimal,
  "unit" varchar(10),
  "ingredientID" int
);

CREATE TABLE "Category" (
  "id" BIGSERIAL PRIMARY KEY,
  "category" varchar(15)
);

CREATE TABLE "Unit" (
  "id" BIGSERIAL PRIMARY KEY,
  "unit" varchar(10)
);

CREATE TABLE "uOWNc" (
  "cateID" int,
  "unitID" int
);

ALTER TABLE "user" ADD FOREIGN KEY ("groupID") REFERENCES "Group" ("groupID");

ALTER TABLE "OwnerShip" ADD FOREIGN KEY ("listID") REFERENCES "ShoppingList" ("listID");

ALTER TABLE "OwnerShip" ADD FOREIGN KEY ("username") REFERENCES "user" ("username");

ALTER TABLE "ShoppingItems" ADD FOREIGN KEY ("listID") REFERENCES "ShoppingList" ("listID");

ALTER TABLE "FavoriteRecipe" ADD FOREIGN KEY ("username") REFERENCES "user" ("username");

ALTER TABLE "Fridge" ADD FOREIGN KEY ("username") REFERENCES "user" ("username");

ALTER TABLE "Ingredient" ADD FOREIGN KEY ("fridgeID") REFERENCES "Fridge" ("fridgeID");

ALTER TABLE "Dish" ADD FOREIGN KEY ("fridgeID") REFERENCES "Fridge" ("fridgeID");

ALTER TABLE "DishIngredient" ADD FOREIGN KEY ("dishID") REFERENCES "Dish" ("dishID");

ALTER TABLE "uOWNc" ADD FOREIGN KEY ("cateID") REFERENCES "Category" ("id");

ALTER TABLE "uOWNc" ADD FOREIGN KEY ("unitID") REFERENCES "Unit" ("id");


INSERT INTO public."Group" ("groupID", "groupName", "leaderUN", "createDate")
VALUES
(1, 'GroupA', 'john_doe', '2024-01-01'),
(2, 'GroupB', 'jane_smith', '2024-02-01');

INSERT INTO public."user" (username, password, name, gender, email, address, "groupID", status)
VALUES
('john_doe', 'password123', 'John Doe', 'M', 'john@example.com', '123 Main St', 1, 'Active'),
('jane_smith', 'password456', 'Jane Smith', 'F', 'jane@example.com', '456 Elm St', 2, 'Active');

INSERT INTO public."user" (username, password, name, gender, email, address, status)
VALUES
('trunganh', '123456', 'Trung Anh', 'Nam', 'trunganh@gmail.com', 'HBT, Ha Noi', 'Active'),
('duyvu', '654321', 'Duc Duy', 'Nam', 'ducduy@gmail.com', 'HBT, Ha Noi', 'Active'),
('dungnguyen', '654321', 'Thuy Dung', 'Nu', 'dungnguyen@gmail.com', 'HBT, Ha Noi', 'Active'),
('bobnguyen', '123456', 'Bob Nguyen', 'Nu', 'bobnguyen@gmail.com', 'HBT, Ha Noi', 'Active'),
('admin', 'admin', 'Admin', 'Nam', 'adFridge@gmail.com', 'HBT, Ha Noi', 'Active');



INSERT INTO public."ShoppingList" ("listID","listName", "totalCost", date)
VALUES
(1, 'Hiii',100, '2024-06-01');

INSERT INTO public."OwnerShip" ("listID", "username", "isOwner")
VALUES
(1, 'john_doe', true);

INSERT INTO public."ShoppingItems" (quantity, "itemName", sub_cost, "boughtBy", "listID", category, unit)
VALUES
(2.5, 'Cabbage', 20, 'john_doe', 1, 'Vegetables', 'kg'),
(1.0, 'Orange', 10, 'john_doe', 1, 'Fruits', 'kg'),
(1.0, 'Chicken', 70, 'john_doe', 1, 'Meat', 'kg');

INSERT INTO public."FavoriteRecipe" ("recipeName", description, ingredient, username)
VALUES
('Tasty Salad', 'Tasty Salad', 'Lettuce, Tomato, Cucumber', 'john_doe'),
('Fruit Smoothie', 'Fruit Smoothie', 'Banana, Strawberry, Milk', 'jane_smith');

INSERT INTO public."Fridge" ("fridgeID", "groupID", username)
VALUES
(1, 1, 'john_doe'),
(2, 2, 'jane_smith');

INSERT INTO public."Ingredient" (name, category, quantity, unit, "purchaseDay", "expiryDay", "fridgeID")
VALUES
('Lettuce', 'Vegetable', 1.0, 'kg', '2024-06-01', '2024-06-10', 1),
('Banana', 'Fruit', 2.0, 'kg', '2024-06-02', '2024-06-12', 2);

INSERT INTO public."Dish" ("dishID", name, meal, "forDate", username, "fridgeID")
VALUES
(1, 'Salad', 'Lunch', '2024-06-03', 'john_doe', 1),
(2, 'Smoothie', 'Breakfast', '2024-06-04', 'jane_smith', 2);

INSERT INTO public."DishIngredient" ("dishID", "ingredientName", quantity, unit, "ingredientID")
VALUES
(1, 'Lettuce', 0.5, 'kg', 1),
(2, 'Banana', 1.0, 'kg', 2);

INSERT INTO public."Category" (category)
VALUES
('Vegetables'),
('Fruits');

INSERT INTO public."Unit" (unit)
VALUES
('kg'),
('g');

INSERT INTO public."uOWNc" ("cateID", "unitID")
VALUES
(1, 1),
(2, 1);
