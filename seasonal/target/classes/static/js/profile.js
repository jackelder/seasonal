
$(document).ready(function(){
  displayMyRecipes();
});

function displayMyRecipes(){
  var username = readUsername();

  $.ajax({
    type: "GET",
    url: `http://localhost:8080/account/${username}/recipes`,
    success: function(recipeArray){
      $.each(recipeArray, function(index, recipe){
        var recipeId = recipe.id;
        var title = recipe.title;
        var author = recipe.author;
        var instructions = recipe.instructions;
        var imageUrl = recipe.imageUrl;
        var sourceUrl = recipe.sourceUrl;
        var ingredients = recipe.ingredients;

        var card = "<div class='card col-md-4'>";
        card += `<a data-toggle='collapse' href='#recipeCollapse${recipeId}' role='button'>`;
        card += `<img src=${imageUrl} class='card-img-top center'>`;
        card += "</a>"
        card += `<p class='cardTitle'>${title}</p>`;

        card += `<div class='collapse' id='recipeCollapse${recipeId}'>`;
        card += "<div class='card-body'>";

        card += "<span class='listCaption'>Ingredients</span>";
        card += "<ul>";

        $.each(ingredients, function(index, ingredient){
        card += `<li>${capitalize(ingredient.name)}</li>`;
        })
        card += "</ul>";

        card += `<p>Author: ${author}</p>`;

        if(sourceUrl){
            card += `<p>Find the complete recipe at <a href='${sourceUrl}'>${author}</a></p>`;
        }

        card += `<button type='button' class='btn btn-secondary center'><a onclick='removeRecipe(${recipeId})'>Remove</a></button>`;
        card += `<div id='removeRecipeButtonLabel${recipeId}'></div>`;

        card += "</div>" //card body

        card += "</div>"; //card

        $("#recipeList").append(card);
      })
    },
    error: function(){
      printError("Error calling web service.");
    }
  }) //ajax

}

function removeRecipe(recipeId){
  var username = readUsername();

  $.ajax({
    type: "DELETE",
    url: `http://localhost:8080/account/${username}/recipe/${recipeId}`,
    success: function(){
      var notice = `<p class='alert alert-secondary'>Removed</p>`;
      $(`#removeRecipeButtonLabel${recipeId}`).append(notice);
      $("#recipeList").empty();
      displayMyRecipes();
    },
    error: function(){
      printError("Error calling web service.");
    }
  }) //ajax
}


function readUsername(){
  return $("#printedUsername").text();
  //returns empty string if nothing found
}

function printErrorMessage(message){
  $('#errorMessages')
     .append($('<li>')
     .attr({class: 'list-group-item list-group-item-danger'})
     .text(message));
}

function capitalize(input){
  return input.charAt(0).toUpperCase() + input.slice(1);
}
