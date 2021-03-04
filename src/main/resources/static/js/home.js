var locationId;
var locationName;
var seasonId;
var seasonName;


$(document).ready(function(){

  $("#ingredientSearchButton").click(function(event){
    displayIngredientSearchResults();
  })

  $("#recipeSearchButton").click(function(){
    displayRecipeSearchResults();
  })
}); //document ready()


function displayIngredientSearchResults(){
  locationId = $("#locationSelection option:selected").val();
  locationName = $("#locationSelection option:selected").text();
  seasonId = $("#seasonSelection option:selected").val();
  seasonName = $("#seasonSelection option:selected").text();

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/results/" + locationId + "/" + seasonId,
    success: function(ingredientArray){

      $("#recipeResults").hide();
      $("#ingredientResultsList").empty();
      $("#ingredientResults h4").text(`Local Foods Available in ${locationName} in ${seasonName}`);

      if(ingredientArray.length == 0){
        $("#ingredientResultsList").append("<div class='alert alert-secondary'><p>It looks like there are no results for this location and season!</p></div>");
      }else{
        $.each(ingredientArray, function(index, ingredient){
          var id = ingredient.id;
          var name = capitalize(ingredient.name);
          var checkItem = "<div class='col-3'>";
          //checkItem += "<li>";
          checkItem += `<input class='form-check-input' type='checkbox' value=${id} name='ingredientCheckbox' checked>`;
          checkItem += `<label class='form-check-label' for='ingredientCheckbox'>`;
          checkItem += name;
          checkItem += "</label></div>";

          $("#ingredientResultsList").append(checkItem);
        }) //each
      } //else
      $("#ingredientResults").show();
    },
    error: function(){
      printError("Error calling web service.");
    }
  }) //ajax
} // displayIngredientSearchResults()


function displayRecipeSearchResults(){
  var ingredientIdArray = [];

  $("input[class=form-check-input]:checked").each(function() {
    var ingredientId = $(this).val();
    ingredientIdArray.push(ingredientId);
  })

  $.ajax({
    type: "POST",
    url: "http://localhost:8080/results/recipes",
    data: JSON.stringify(ingredientIdArray),
    headers: {
      "Accept": "application/json",
      "Content-Type":"application/json"
    },
    success: function(recipeResultArray){

      $("#recipeResultsList").empty();
      $("#recipeResults h4").text(`Ranked Recipes for ${locationName} in ${seasonName}`);

      $.each(recipeResultArray, function(index, recipeResult){
        var recipeId = recipeResult.id;
        var title = recipeResult.title;
        var score = recipeResult.score;
        var author = recipeResult.author;
        var instructions = recipeResult.instructions;
        var imageUrl = recipeResult.imageUrl;
        var sourceUrl = recipeResult.sourceUrl;
        var availableIngredients = recipeResult.availableIngredients;
        var otherIngredients = recipeResult.otherIngredients;

        var card = "<div class='card col-md-4'>";
        card += `<a data-toggle='collapse' href='#recipeCollapse${recipeId}' role='button'>`;
        card += `<img src=${imageUrl} class='card-img-top center'>`;
        card += "</a>"
        card += `<p class='cardTitle'>${title} (<span class='availabilityScore'>${score}%</span>)</p>`;

        card += `<div class='collapse' id='recipeCollapse${recipeId}'>`;
        card += "<div class='card-body'>";

        card += "<span class='listCaption'>Available Ingredients</span>";
        card += "<ul>";

        $.each(availableIngredients, function(index, ingredient){
        card += `<li><span class='availableIngredient'>${capitalize(ingredient.name)}</span></li>`;
        })
        card += "</ul>"
        card += "<span class='listCaption'>Additional Ingredients</span>";
        card += "<ul>";
        $.each(otherIngredients, function(index, ingredient){
        card += `<li>${capitalize(ingredient.name)}</li>`;
        })
        card += "</ul>";

        card += `<p>Author: ${author}</p>`;

        if(sourceUrl){
            card += `<p>Find the complete recipe at <a href='${sourceUrl}'>${author}</a></p>`;
        }

        if(readUsername() !== ""){
        card += `<button type='button' class='btn btn-primary center'><a onclick='saveRecipe(${recipeId})'>Save</a></button>`
        card += `<div id='saveRecipeButtonLabel${recipeId}'></div>`;
        }

        card += "</div>" //card body

        card += "</div>"; //card

        $("#recipeResultsList").append(card);
      })

      $("#recipeResults").show();
    },
    error: function(){
      printError("Error calling web service.");
    }
  }) //ajax
} //displayRecipeSearchResults()

function saveRecipe(recipeId){
  var username = readUsername();

    $.ajax({
      type: "GET",
      url: `http://localhost:8080/account/${username}/recipe/${recipeId}`,
      success: function(response){
        var notice = `<p class='alert alert-primary'>${response}</p>`;
        $(`#saveRecipeButtonLabel${recipeId}`).append(notice);
      },
      error: function(){
        printError("Error calling web service.");
      }
    }) //ajax
}

function capitalize(input){
  return input.charAt(0).toUpperCase() + input.slice(1);
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
