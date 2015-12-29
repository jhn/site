-- name: create-photo!
-- creates a new user record
INSERT INTO photos
(id, description, url)
VALUES (:id, :description, :url)

-- name: get-photos
-- gets all photos
SELECT * FROM photos

-- name: update-photo!
-- update an existing photo record
UPDATE photos
SET description = :description, url = :url
WHERE id = :id

-- name: get-photo
-- retrieve a photo given the id.
SELECT * FROM photos
WHERE id = :id

-- name: delete-photo!
-- delete a photo given the id
DELETE FROM photos
WHERE id = :id
