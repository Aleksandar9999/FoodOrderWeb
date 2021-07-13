Vue.component("registration", {
	data: function () {
		    return {
		      id : -1,
		      user: {
                 username:'',
                 password:'',
                 name:'',
                 surname:'',
                 //dateOfBirth:null,
                 userRole:''
              }
		    }
	},
	template: ` 
	<div id="loginForm">
    <p id="title">Registracija</p>
    <form onsubmit="registerUser">
            <div class="field">
                <input id="txtName" placeholder="Ime" type="text" v-model = "user.name">
            </div>
 
            <div class="field">
                <input id="txtSurname" placeholder="Prezime" type="text" v-model = "user.surname">
            </div>
            
            <div  style="align-content: flex-end;">
            <p style="display: inline; margin-right:25px">Pol:</p>
                        <input type="radio" id="male" name="gender" value="male">
                        <label for="male">Muski</label>
                        <input type="radio" id="female" name="gender" value="female">
                        <label for="female">Zenski</label><br>
            
            </div>
                <p>Datum rodjenja</p>
            <div class="field">    
                <input id="txtUsername" placeholder="Korisnicko ime" type="text" v-model = "user.username">
            </div>
            <div class="field">    
                <input class="txtPassword" placeholder="Lozinka" type="text" v-model = "user.password">
            </div>
            <div class="field">
            <input class="txtPassword" placeholder="Ponovo lozinka" type="text">
            </div>
            <div class="field">
                <input type="submit" value="Potvrdi">
            </div>
            </form>
        </div>
	
`
	, 
	methods : {
		registerUser : function (event) {
			event.preventDefault();
			axios.post('/rest/users/buyers', this.user).
			then(response => {
                router.push(`/`); 
            }).catch(err=> {
                   console.log(err.data)
                });
		}
	}
});
// <input id="txtDateOfBirth" type="date" v-model = "user.dateOfBirth">