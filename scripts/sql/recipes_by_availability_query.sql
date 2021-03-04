SELECT
r.*
FROM Recipe r
JOIN RecipeIngredient ri ON r.id = ri.recipeId
JOIN Ingredient i ON i.id = ri.ingredientId
JOIN Availability a ON i.id = a.ingredientId
WHERE a.locationId = 'MN' AND a.seasonId = 1
GROUP BY r.id
ORDER BY COUNT(i) DESC
LIMIT 500;


