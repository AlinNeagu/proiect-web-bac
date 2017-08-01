

var app = angular.module('myApp', []);

app.controller("ListController", ['$scope','$http', function($scope,$http) {
    
	$scope.obligatorie=false;
	$scope.alegere=false;
	$scope.materna=false;
	$scope.moderna=false;
	
	$scope.choices={};
	$scope.specs;
	$scope.profil={
		specializari:[]
	};
	
	$scope.i={};
	$scope.i.nume={};
	$scope.i.denumireProfil={};
	$scope.showElevi=true;
	$scope.showLicee=false;
	$scope.showProbe=false;
	$scope.showProfiluri=false;
	$scope.showSpecializari=false;
	$scope.specializare={};
	$scope.elev={};
	$scope.message={};
	$scope.canDelete=false;
	//------------------- DELETE ----------------------------
	
	$scope.deleteElev=function(id){
		var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/deleteElev',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				 	idElev:(id)
			  }
		  };
		$http(req).then(function(response){
			$scope.updateElevi();
		});
		
	};
	
	$scope.deleteLiceu=function(id){
		var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/deleteLiceu',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				 	id:(id)
			  }
		  };
		$http(req).then(function(response){
			rightOrWrong(response.data);
		});
		
	};
	
	$scope.deleteDisciplina=function(id){
		var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/deleteDisciplina',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				 	id:(id)
			  }
		  };
		$http(req).then(function(response){
			rightOrWrong(response.data);
		});
		
	};
	
	$scope.deleteProfil=function(id){
		var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/deleteProfil',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				 	id:(id)
			  }
		  };
		$http(req).then(function(response){
			rightOrWrong(response.data);
		});
		
	};
	$scope.selectedSpecializareId;
	$scope.setDeleteModalData=function(id){
		var req={
				  method: 'GET',
				  url: 'http://localhost:8080/ProiectBAC/canDeleteSpecializare',
				  headers: {
					  'Content-Type' : undefined
				  },
				  params : {
						id:(id)
				  }
	  	};
		$scope.selectedSpecializareId=id;
		$http(req).then(function(response){
			$scope.message.head="Vrei sa stergi o specializare ? ";
			$scope.message.body="Esti sigur ca vrei sa stergi aceasta specializare? Toti elevii si probele specializarii respective vor fi sterse de asemenea!!!";
			$scope.message.canAccept=true;
			$scope.showModalDelete();
		})
	}
	
	$scope.deleteSpecializare=function(){
		
		
			var req = {
				  method: 'GET',
				  url: 'http://localhost:8080/ProiectBAC/deleteSpecializare',
				  headers: {
					  'Content-Type' : undefined
				  },
				  params : {
						id:($scope.selectedSpecializareId)
				  }
			  };
			$http(req).then(function(response){
				rightOrWrong(response.data);
			});
		
	};
	
	//------------------END DELETE --------------------------
	
	//------------------- UPDATE -----------------------------
	
	
	$scope.clickEdit=function(id,idn,details){
		
		
		var elementId=idn.concat("-edit".concat(id));
		if(document.getElementById(elementId).className=="btn btn-primary edit"){
			document.getElementById(elementId).className="btn btn-success edit";
			document.getElementById(idn.concat("-name-".concat(id))).style.display="none";
			document.getElementById(idn.concat("-name-txt-".concat(id))).style.display="inline-block";
			var elements = document.getElementsByClassName('edit');
			for(var i=0; i<elements.length; i++) {
				if(!(elements[i].id===elementId))
					elements[i].style.display="none";
			}
			document.getElementById(elementId).value="done";
		}
		else{
			document.getElementById(elementId).className="btn btn-primary edit";
			document.getElementById(elementId).value="edit";
			document.getElementById(idn.concat("-name-".concat(id))).style.display="inline-block";
			document.getElementById(idn.concat("-name-txt-".concat(id))).style.display="none";
			var elements = document.getElementsByClassName('edit');
			for(var i=0; i<elements.length; i++) {
				if(!(elements[i].id===elementId))
					elements[i].style.display="";
				
				
			
			}
			if(idn==='profil'){
				var req = {
					  method: 'GET',
					  url: 'http://localhost:8080/ProiectBAC/updateProfil',
					  headers: {
						  'Content-Type' : undefined
					  },
					  params : {
							idProfil:(id),
							nume:(details.denumireProfil)
					  }
			  	};
				$http(req).then(function(response){
					$scope.getProfiluri();
				});
			}
			
				if(idn==='liceu'){
				var req = {
					  method: 'GET',
					  url: 'http://localhost:8080/ProiectBAC/updateLiceu',
					  headers: {
						  'Content-Type' : undefined
					  },
					  params : {
							idLiceu:(id),
							nume:(details.nume)
					  }
			  	};
				$http(req).then(function(response){
					$scope.takeLicee();
				});
			}
			
				if(idn==='specializare'){
				var req = {
					  method: 'GET',
					  url: 'http://localhost:8080/ProiectBAC/updateSpecializare',
					  headers: {
						  'Content-Type' : undefined
					  },
					  params : {
							idSpecializare:(id),
							nume:(details.nume)
					  }
			  	};
				$http(req).then(function(response){
					$scope.getSpecializari();
				});
			}
		}
		
	}
	

	
	
	//------------------- END UPDATE  ------------------------
	
	
	$scope.allProfils=[];
	
	//$scope.unitati=[{id:"",nume:"asd"}];
	$scope.profiluri=[{id:"",nume:"asdasd"}];
	$scope.specializari=[{id:"",nume:"asdasdasd"}];
	
   //--JSON pentru Specializari cu functie-----
 	$scope.index=0;
    $scope.specializari=[{ 'id':$scope.index,
                          'liceuSpecializare':''
                         }];
    $scope.denumireDisciplina="";
    $scope.increment= function(){
        if ($scope.specializari[$scope.index].liceuSpecializare != ''){
            $scope.index++;
            var denumireSpecializare={};
            
            denumireSpecializare.id=$scope.index;
            denumireSpecializare.liceuSpecializare='';
            
            $scope.specializari.push(denumireSpecializare);
            
            
        }
            
            
    };
    
	$scope.download=function(){
        console.log("intrare in download");
   
        $http({
            method: 'GET',
            url: 'http://localhost:8080/ProiectBAC/export', 
            responseType: "blob",
            headers: {
                'Accept': "application/xls"
            }
        })
		.then(function(response){
			console.log("Report content: ",response);
          
            var blob = new Blob([response.data], { type: "application/xls" });
            saveAs(blob, "ExportPeTara.xls");
		});
      
        
        
        
    }
	
	var rightOrWrong=function(ok){
		if(ok==true){
			document.getElementById("working").style.display="block";
			document.getElementById("right").style.display="inline-block";
			document.getElementById("wrong").style.display="none";
		}
		else{
			document.getElementById("working").style.display="block";
			document.getElementById("right").style.display="none";
			document.getElementById("wrong").style.display="inline-block";
		}
		document.getElementById("working").style.height="auto";
		
		//after 1000 ms close the mark
		setTimeout(function(){
			document.getElementById("working").style.height="0px";
			document.getElementById("working").style.display="block";
			document.getElementById("right").style.display="none";
			document.getElementById("wrong").style.display="none";
		}, 1000);
	};

	
	//------------------- LOGIN---------------------------------------
	$scope.isLogin=0;
	$scope.login=function(username,password){
		var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/checkUser',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				 	user:(username),
				  	pass:(password)
			  }
		  };
		$http(req).then(function(response){
			$scope.loginResponse=response.data;
			
			if(response.data==true)
				$scope.isLogin=1;
		});
		
		$scope.closeLoginPopUp();
		
	};
	
	$scope.showLoginPopUp=function(){
		document.getElementById("logIn").style.display="inline-block";	
	};
	
	$scope.closeLoginPopUp=function(){
		document.getElementById("logIn").style.display="none";	
	};
	//---------------------- END LOGIN -------------------------------
	
	
	
	//---------------------- ADD PROBE -------------------------------
	
	
	$scope.showModalProbe=function(){
		
		document.getElementById("modalProbe").style.display="inline-block";
		$scope.getSpecializari();
		$scope.getDiscipline();
	};
	
	$scope.closeModalProbe=function(){
		document.getElementById("modalProbe").style.display="none";	
	};
	
	$scope.addProba=function(idSpec,idDisc,materna,moderna,obligatorie){
		  var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/addProba',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				  proba:{
					  idSpec: ( idSpec ),
					  idDisc: (idDisc),
					  obligatorie:(obligatorie),
					  moderna:(moderna),
					  materna:(materna)
				  }
			  }
		  };
		 

		$http(req).then(function(response){
			rightOrWrong(response.data);
		});
		
		$scope.closeModalProbe();
	}
	
	
	$scope.getSpecializari=function(){
		$http.get("http://localhost:8080/ProiectBAC/getSpecializari")
			.then(function(response){
				$scope.specializariForProbe=response.data;	  
		  });	
	};
	$scope.getDiscipline=function(){
		$http.get("http://localhost:8080/ProiectBAC/getDiscipline")
			.then(function(response){
				$scope.disciplinaForProba=response.data;	  
		  });	
	};
	
	//---------------------- END PROBE -------------------------------
	
	
	//-------------   ADD ELEV ----------------------------------------
	$scope.takeLicee = function(){
		$http.get("http://localhost:8080/ProiectBAC/licee")
			.then(function(response){
				$scope.choices.Liceu=response.data.licee;
				if(!$scope.adaugare)
				{
					for(var i=0;i<$scope.choices.Liceu.length;i++){
						if($scope.choices.Liceu[i].id==$scope.elevEditat.liceu.id)
						{	
							$scope.bindUnitate(i);
							$scope.getProfiluriForUnitate($scope.elevEditat.liceu.nume);
						}
					}
				}
		  });	
	};
	
	$scope.getProfiluriForUnitate=function(denumireLiceu){
		  var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/getProfiluriForUnitate',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				  unitate: ( denumireLiceu )   
			  }
		  };


		$http(req).then(function(response){
			if($scope.liceu)
				$scope.liceu.profil=response.data;
			$scope.profiluri=response.data;
			if(!$scope.adaugare){
				var w=0;
				for(w=0;w<$scope.profiluri.length;w++){
					if($scope.profiluri[w].idProfil==$scope.elevEditat.profil.idProfil)
					{
						$scope.bindProfil(w);
						$scope.getSpecializariForProfil($scope.elevEditat.profil.denumireProfil);
					}
				}
			}
		});
	};
	
	$scope.getProfiluri=function(){
		$http.get("http://localhost:8080/ProiectBAC/getProfiluri")
			.then(function(response){
		$scope.allProfils=response.data;
		 if(! $scope.adaugare){
             if($scope.liceuEditat){
                 for(var i=0;i<$scope.liceuEditat.idProfil.length;i++){
                      $scope.cautaId($scope.liceuEditat.idProfil[i],i);
                 }
             };
             
             for(var i=0;i<$scope.allProfils.length;i++){
                    if($scope.specEditata.profil.idProfil==$scope.allProfils[i].idProfil){
                     $scope.bindProf(i);
                 }
             }
			
		 }
	});
	};
	
	$scope.getSpecializariForProfil=function(denumireProfil){
		  var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/getSpecializariForProfil',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				  profil: ( denumireProfil )   
			  }
		  };


		$http(req).then(function(response){
			$scope.profil.specializare=response.data;
			$scope.specs=response.data;
			if(!$scope.adaugare){
				for(var i=0;i<$scope.specs.length;i++){
					if($scope.specs[i].id==$scope.elevEditat.spec.id){
						{
							$scope.bindSpecializare(i);
							$scope.getProbeForSpecializare($scope.elevEditat.spec.nume);
						}
					}
				}
			}
		});
	};
    /*
	//--get Profil For Secified Specializare
    $scope.getProfilForSpecializare=fuction(denumireSpecializare){
         var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/getProfilForSpecializare',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				  spec: ( denumireSpecializare )   
			  }
		  };
    };
    
*/    
	//get probs for a specified 'specializare'
	$scope.getProbeForSpecializare=function(denumireSpecializare){
		  var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/getProbeForSpecializare',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				  specializare: ( denumireSpecializare )   
			  }
		  };

		
		$http(req).then(function(response){
			$scope.specializare.probaObligatorie=response.data.probaObligatorie;
			$scope.specializare.probaAlegere=response.data.probaAlegere;
			$scope.specializare.LimbaMaterna=response.data.limbaMaterna;
			$scope.specializare.LimbaModerna=response.data.limbaModerna;
			var o,a,mo,ma;
			if(!$scope.adaugare){
				for(var i=0;i<$scope.specializare.probaObligatorie.length;i++){
					if($scope.specializare.probaObligatorie[i].id==$scope.elevEditat.probaObligatorie.id)
						o=i;
				}
				for(var i=0;i<$scope.specializare.probaAlegere.length;i++){
					if($scope.specializare.probaAlegere[i].id==$scope.elevEditat.probaAlegere.id)
						a=i;
				}
				for(var i=0;i<$scope.specializare.LimbaModerna.length;i++){
					if($scope.specializare.LimbaModerna[i].id==$scope.elevEditat.limbaModerna.id)
						mo=i;
				}
				for(var i=0;i<$scope.specializare.LimbaMaterna.length;i++){
					if($scope.specializare.LimbaMaterna[i].id==$scope.elevEditat.limbaMaterna.id)
						ma=i;
				}
				$scope.bindProbe(a,mo,ma,o);
			}
		});
	};
	
		$scope.addElev=function(){
		  
		
			var probe=[];
			
			
			probe.push($scope.probaObligatorie);
			probe.push($scope.limbaModerna);
			probe.push($scope.limbaMaterna);
			probe.push($scope.probaAlegere);
		
			$scope.elev.idSpecializare=$scope.specializare.id;
			$scope.elev.idUnitate=$scope.liceu.id;
			$scope.elev.probe=probe;

			console.log($scope.elev);
			var req = {
			  method: 'GET',
			  url: 'http://localhost:8080/ProiectBAC/addElev',
			  headers: {
				  'Content-Type' : undefined
			  },
			  params : {
				  elev:($scope.elev)
			  }
		  };
			$http(req).then(function(response){
				$scope.updateElevi();
				rightOrWrong(response.data);
		});
		$scope.closeModalElev();
			
		
	};
    //----end ADD elev
    
    //--JSON pentru Profiluri cu functie pt repeat-----
     $scope.indexProfil=0;
    $scope.profiluriLicee=[{ 'id':$scope.indexProfil,
                          'liceuProfil':''
                         }];
    $scope.liceuProfil="";
    $scope.incrementation= function(){
        if ($scope.profiluriLicee[$scope.indexProfil].liceuProfil != ''){
            $scope.indexProfil++;
            var profil={};
            
            profil.id=$scope.indexProfil;
            profil.liceuProfil='';
            
            $scope.profiluriLicee.push(profil);   
        }         
    };
    
    //----
    
	
	
    //scot  Profiluri
	//this is a shortcut for an http request and it works only if you dont need to send data in java 
    //if you don't put the request in a function it will run only once when the page start 
	
	$scope.takeProfiluri=function(){
		$http.get("http://localhost:8080/ProiectBAC/profiluri")
		.then(function(response){
			$scope.profiluri=response.data.profiluri;
		});
    };
  
   
    $scope.posteaza=function(){
       $scope.postare={
           'nume': 'hello',
           'email': 'tra@yahoo.com',
           'parere': 'ehfueff3',
       }
    };
   
    //---------JSON pentru adaugare liceu--------------------------------
    
    
    $scope.licee=[{
                  'numeLiceu':'',
                  'judet':'',
                  'profil':[]
     }];
                                  
 var profilId= undefined;
    
 //----JSON pt adaugare profiluri noi-----
   $scope.profiluri=[{ 'idProfil':'',
                        'denumireProfil':'',
                        'specializari':[{'id':'',
                                        'numeSpecializare':''
                                        }]
                     }];
               
   //------JSON pt profil existent de adaugat in concordanta cu liceul---     
     $scope.profiluriLicee=[{ 'idProfil':'',
                          'denumireProfil':''
                         }];
    
    
    //--------JSON pt adaugare profiluri-----------
    $scope.profiluri=[{
        'denumireProfil':'',
        'specializari':[]
    }];
    
/*    
    var profilNou = {
        'idProfil' : 2,
        'denumireProfil' : 'aaa'
    };
    
    $scope.profiluriLicee.push(profiNou)
 */                        
//---------------dependable dropdown boxes------------------------------
   


    //-------------------------------------------------------------
    

	
	
	//add 'disciplina' in the database 
	$scope.insertDisciplina = function(){
	  var req = {
		  method: 'GET',
		  url: 'http://localhost:8080/ProiectBAC/disciplina',
		  headers: {
			  'Content-Type' : undefined
		  },
		  params : {
			  disciplina: ( $scope.denumireDisciplina )   
		  }
      };

	$http(req).then(function(response){
		rightOrWrong(response.data);
	   });
		$scope.closeModalDisciplina();
	};
    /*
    //--ADD Specializare
    $scope.insertSpecializare = function(){
		console.log("Id profil ",  $scope.profilAles.idProfil);
        console.log("profil ales ",  $scope.profilAles);
        console.log("den spec", $scope.denumireSpecializare);
	  var req = {
		  method: 'GET',
		  url: 'http://localhost:8080/ProiectBAC/specializare',
		  headers: {
			  'Content-Type' : undefined
		  },
		  params : {
			  specializare: ( $scope.denumireSpecializare ),   
              idProfil: ( $scope.profilAles.idProfil )
		  }
        
      };

	$http(req).then(function(response){
			rightOrWrong(response.data);
	   });
		$scope.closeModalSpecializare();
	};
    
    */
    $scope.insertSpecializare = function(idProfil,denumireSpec){
		
	  var req = {
		  method: 'GET',
		  url: 'http://localhost:8080/ProiectBAC/specializare',
		  headers: {
			  'Content-Type' : undefined
		  },
		  params : {
			  specializare: ( denumireSpec ),   
              idProfil: ( idProfil )
		  }
      };

	$http(req).then(function(response){
			rightOrWrong(response.data);
	   });
		$scope.closeModalSpecializare();
	};
    
    
    
     //--EDIT Specializare
    $scope.editSpecializare = function(){
		
        
       // $scope.specEditata.idProfil.currentId=$scope.currentProfilId;
         console.log($scope.specEditata);
      //  console.log(" Specializareeee editata:    "  $scope.specEditata);
       // console.log(" Specializareeee aleasa :    "  $scope.specAleasa);
	  var req = {
		  method: 'GET',
		  url: 'http://localhost:8080/ProiectBAC/editSpecializare',
		  headers: {
			  'Content-Type' : undefined
		  },
		  params : {
			  specializare: $scope.specEditata
              
		  }
      };
        

	$http(req).then(function(response){
	   if(response.data==false){
           alert("Aceasta specializare exista deja!");
       }
        
	   });
		$scope.closeModalSpecializare();
	};
    
    //----ADD 'liceu' into database
    $scope.insertLiceu = function(){
  
      
       //create a new array (idProfiles) with idProfile from $scope.profiluriLiceee
      var idProfiles = [];
      for(var i=0;i < $scope.profiluriLicee.length;i++){
          console.log("!!! Profiluri licee: ", $scope.profiluriLicee[i].liceuProfil);
          idProfiles.push($scope.profiluriLicee[i].liceuProfil.idProfil);
      }
      //$scope.profiluriLicee
      //add the array created below to the liceu
      $scope.liceu.idProfil = idProfiles;
        
	  var req = {
		  method: 'GET',
		  url: 'http://localhost:8080/ProiectBAC/liceu',
		  headers: {
			  'Content-Type' : undefined
		  },
		  params : {
			liceu:{ 
                'nume': ( $scope.liceu.numeLiceu ),
                'judet': ( $scope.liceu.judet),
                'idProfilInt':($scope.liceu.idProfil)
		      }
          }
      };
	
    
	$http(req).then(function(response){
		rightOrWrong(response.data);
	});
		$scope.closeModalLiceu();
	};
	
    //---end ADD liceu
    
     //----EDIT 'liceu' into database
    $scope.editLiceu = function(){
  
      
       //create a new array (idProfiles) with idProfile from $scope.profiluriLiceee
/*      var idProfiles = [];
      for(var i=0;i < $scope.profiluriLicee.length;i++){
          console.log("!!! Profiluri licee: ", $scope.liceuEditat);
          idProfiles.push($scope.profiluriLicee[i].liceuProfil.idProfil);
      }*/
      //$scope.profiluriLicee
      //add the array created below to the liceu
     /* $scope.liceu.idProfilInt = idProfiles;*/
        
        for(var i=0;i<$scope.liceuEditat.idProfil.length;i++){
            $scope.liceuEditat.idProfil[i].currentId=$scope.currentLiceuId[i];
        }
        console.log($scope.liceuEditat);
	  var req = {
		  method: 'GET',
		  url: 'http://localhost:8080/ProiectBAC/editLiceu',
		  headers: {
			  'Content-Type' : undefined
		  },
		  params : {
			liceu:$scope.liceuEditat
/*              { 
               'nume': ( $scope.liceuEditat.nume ),
                'judet': ( $scope.liceuEditat.judet),
                'idProfilInt':($scope.liceuEditat.idProfil[$index])
		      }*/
          }
      };
	
    
	$http(req).then(function(response){
		rightOrWrong(response.data);
	});
		$scope.closeModalLiceu();
	};
	
    //---end EDIT liceu
    
    
    //---ADD  PROFIL into database
   $scope.insertProfil = function(){
  
	  var req = {
		  method: 'GET',
		  url: 'http://localhost:8080/ProiectBAC/profil',
		  headers: {
			  'Content-Type' : undefined
		  },
		  params : {
			  profil: ( $scope.denumireProfil )   
		  }
      };
	

	$http(req).then(function(response){
		rightOrWrong(response.data);
	});
	   $scope.closeModalProfil();
	};
 
	$scope.items=[];
	
	//this is a shortcut for an http request and it works only if you dont need to send data in java 
    //if you don't put the request in a function it will run only once when the page start 
	$http.get("http://localhost:8080/ProiectBAC/elevi2")
	.then(function(response){
		$scope.items=response.data;
	});
	
	$scope.updateElevi=function(){
			$http.get("http://localhost:8080/ProiectBAC/elevi2")
	.then(function(response){
		$scope.items=response.data;
	});
	};
	
//--------------------------------------	
	
	$scope.sortare = 'numeElev';
	
	$scope.showDetails = function (item){
		$scope.selectedItem = item;
	}
	$scope.veziNotele = function(i){
		$scope.selectedItem = item;
	}
	$scope.show = false;
	
	$scope.remove = function(item){
            var i = $scope.items.indexOf(item);
			$scope.items.splice(i,1);
        }
	
	$scope.addNew = function(item){
            $scope.items.push(item);

          //  alert($scope.items);
            $scope.item={};
        } 
	
	$scope.adaugare=true;
    $scope.liceuEditat;
    $scope.specEditata;
	$scope.currentLiceuId=[];
    $scope.currenProfilId=[];
	
	$scope.edit= function(liceu){
        $scope.adaugare=false;
        $scope.showModalLiceu();
        $scope.liceuEditat=liceu;
        $scope.getProfiluri();
        
    }
    $scope.edited= function(specializare){
        $scope.adaugare=false;
        $scope.showModalSpecializare();
        $scope.specEditata=specializare;
        $scope.getProfiluri();
        
    }
    
    $scope.bindProf=function(id){
        $scope.specEditata.profil=$scope.allProfils[id];
    }
    
    $scope.cautaId = function(profilAles,id){
        $scope.profiluri;
        
        for(var i=0;i<$scope.allProfils.length;i++){
            if($scope.allProfils[i].idProfil==profilAles.idProfil){
                $scope.liceuEditat.idProfil[id]=$scope.allProfils[i];
                $scope.currentLiceuId[id]=$scope.allProfils[i].idProfil;
            }
        }
        
      /* 
            if($scope.allProfils.idProfil==profilAles.idProfil){
                $scope.specEditata.idProfil=$scope.allProfils;
                $scope.currentProfilId=$scope.allProfils.idProfil;
            }
       */         
        
    }
	
	
	$scope.elevEditat;
	$scope.editElev=function(elev){
		$scope.adaugare=false;
		$scope.elevEditat=elev;
		$scope.showModalElev();
		$scope.takeLicee();
		
		
	}
	$scope.bindUnitate=function(id){
		$scope.elevEditat.liceu=$scope.choices.Liceu[id];
	}
	$scope.bindProfil=function(id){
		$scope.elevEditat.profil=$scope.profiluri[id];
	}
	$scope.bindSpecializare=function(id){
		$scope.elevEditat.spec=$scope.specs[id];
	}
	$scope.note={};
	$scope.bindProbe=function(alegere,moderna,materna,obligatorie){
		
		var pObligatorie={};
		var pAlegere={};
		var lMaterna={};
		var lModerna={};
		
		pObligatorie.nota=$scope.elevEditat.probaObligatorie.nota;
		pObligatorie.notaContestatie=$scope.elevEditat.probaObligatorie.notaContestatie;
		
		pAlegere.nota=$scope.elevEditat.probaAlegere.nota;
		pAlegere.notaContestatie=$scope.elevEditat.probaAlegere.notaContestatie;
		
		lMaterna.nota=$scope.elevEditat.limbaMaterna.nota;
		lMaterna.notaContestatie=$scope.elevEditat.limbaMaterna.notaContestatie;
		
		lModerna.nota=$scope.elevEditat.limbaModerna.nota;
		lModerna.notaContestatie=$scope.elevEditat.limbaModerna.notaContestatie;
		
		$scope.note.pObligatorie=pObligatorie;
		$scope.note.pAlegere=pAlegere;
		$scope.note.lMaterna=lMaterna;
		$scope.note.lModerna=lModerna;
		
		$scope.elevEditat.probaObligatorie=$scope.specializare.probaObligatorie[obligatorie];
		$scope.elevEditat.probaAlegere=$scope.specializare.probaAlegere[alegere];
		$scope.elevEditat.limbaModerna=$scope.specializare.LimbaModerna[moderna];
		$scope.elevEditat.limbaMaterna=$scope.specializare.LimbaMaterna[materna];
	}
	
	$scope.editElevDB=function(){
		
		
		$scope.elevEditat.probaObligatorie.nota=$scope.note.pObligatorie.nota;
		$scope.elevEditat.probaAlegere.nota=$scope.note.pAlegere.nota;
		$scope.elevEditat.limbaModerna.nota=$scope.note.lModerna.nota;
		$scope.elevEditat.limbaMaterna.nota=$scope.note.lMaterna.nota;
			
		$scope.elevEditat.probaObligatorie.notaContestatie=$scope.note.pObligatorie.notaContestatie;
		$scope.elevEditat.probaAlegere.notaContestatie=$scope.note.pAlegere.notaContestatie;
		$scope.elevEditat.limbaModerna.notaContestatie=$scope.note.lModerna.notaContestatie;
		$scope.elevEditat.limbaMaterna.notaContestatie=$scope.note.lMaterna.notaContestatie;
		
		var req={
			
			
			
			url:"http://localhost:8080/ProiectBAC/editElev",
			method:"GET",
			params:{
				elev:($scope.elevEditat)
			}
		}
		
		$http(req).then(function(response){
			
		});
		$scope.adaugare=true;
	}
	
	$scope.showModalElev=function(){

		document.getElementById("myModal").style.display="inline-block";
	};
	$scope.closeModalElev=function(){

		document.getElementById("myModal").style.display="none";
	};
	
	$scope.showModalDisciplina=function(){

		document.getElementById("modalDisciplina").style.display="inline-block";
	};
	$scope.closeModalDisciplina=function(){

		document.getElementById("modalDisciplina").style.display="none";
	};
	
	$scope.showModalSpecializare=function(){

		document.getElementById("modalSpecializare").style.display="inline-block";
	};
	$scope.closeModalSpecializare=function(){

		document.getElementById("modalSpecializare").style.display="none";
	};
	
	$scope.showModalLiceu=function(){

		document.getElementById("modalLiceu").style.display="inline-block";
	};
	$scope.closeModalLiceu=function(){

		document.getElementById("modalLiceu").style.display="none";
	};
	
	$scope.showModalProfil=function(){

		document.getElementById("modalProfil").style.display="inline-block";
	};
	$scope.closeModalProfil=function(){

		document.getElementById("modalProfil").style.display="none";
	};
	
	$scope.showModalDelete=function(){
		document.getElementById("modalDelete").style.display="inline-block";
	};
	
	$scope.closeModalDelete=function(){
		document.getElementById("modalDelete").style.display="none";
	};
	
	$scope.showOtherView=function(elevi,probe,profiluri,spec,licee){
		$scope.showElevi=elevi;
		$scope.showLicee=licee;
		$scope.showProbe=probe;
		$scope.showProfiluri=profiluri;
		$scope.showSpecializari=spec;
	};
	
}]);

app.directive("loginPopup", function() {
	return {
		restrict : 'E',
		templateUrl : "login-popup.html"
	}
});

app.directive("displayElevi", function() {
	return {
		restrict : 'E',
		templateUrl : "directives/display-elevi.html"
	}
});
app.directive("displayProfiluri", function() {
	return {
		restrict : 'E',
		templateUrl : "directives/display-profiluri.html"
	}
});
app.directive("displaySpecializari", function() {
	return {
		restrict : 'E',
		templateUrl : "directives/display-specializari.html"
	}
});

app.directive("displayLicee", function() {
	return {
		restrict : 'E',
		templateUrl : "directives/display-licee.html"
	}
});
app.directive("displayProbe", function() {
	return {
		restrict : 'E',
		templateUrl : "directives/display-probe.html"
	}
});

window.onload=function(){

	window.onclick = function(event) {
		if (event.target == modalProfil) {
			modalProfil.style.display = "none";
           
		}
	}
};






