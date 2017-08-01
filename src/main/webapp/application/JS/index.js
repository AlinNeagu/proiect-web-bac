// Define a new module for our app
 var module2 = angular.module('module2',[]);

// Create the instant search filter

module2.filter('searchFor', function(){

	// All filters must return a function. The first parameter
	// is the data that is to be filtered, and the second is an
	// argument that may be passed with a colon (searchFor:searchString)

	return function(arr, searchString){

		if(!searchString){
			return arr;
		}

		var result = [];

		searchString = searchString.toLowerCase();

		// Using the forEach helper method to loop through the array
		angular.forEach(arr, function(item){

			if(item.numeElev.toLowerCase().indexOf(searchString) !== -1){
				result.push(item);
			}

		});

		return result;
	};

});

// The controller

function InstantSearchController($scope,$http){

	
		
$scope.choices={
 
      "Liceu": [
        {
          "nume": "Colegiul Emil Racovita",
          "specializare": [
           {      
              "nume":"Matematica-Informatica",
               "probaObligatorie": "Matematica",
               "probaAlegere": ["Informatica","Fizica","Chimie","Biologie"]
           },
           {  
               "nume": "Stiinte ale Naturii",
               "probaObligatorie": "Matematica",
               "probaAlegere": ["Informatica","Fizica","Chimie","Biologie"]
           },
           {   
               "nume": "Filologie",
               "probaObligatorie": "Istorie",
               "probaAlegere": [ "Geografie", "Psihologie","Filosofie"]
           },
		   {
		    "nume": "Stiinte Sociale",
		    "probaObligatorie": "Istorie",
		    "probaAlegere": [ "Geografie", "Psihologie","Filosofie"]
		   }
          ],
        },
        {
         "nume":"Colegiul Grigore Moisil",
         "specializare": [
               {
                "nume":"Matematica-Informatica",
               "probaObligatorie": "Matematica",
               "probaAlegere": ["Informatica","Fizica","Chimie","Biologie"]
               
               }
            ] 
        }
      ]
    }
 }

