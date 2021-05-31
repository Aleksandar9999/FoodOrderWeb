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
	<form>
            <table>
                <tr>
                    <td>
                        <p>Ime</p>
                    </td>
                    <td>
                        <input id="txtName" type="text" v-model = "user.name">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Prezime</p>
                    </td>
                    <td>
                        <input id="txtSurname" type="text" v-model = "user.surname">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Pol</p>
                    </td>
                    <td style="align-content: flex-end;">
                        <input type="radio" id="male" name="gender" value="male">
                        <label for="male">Muski</label>
                        <input type="radio" id="female" name="gender" value="female">
                        <label for="female">Zenski</label><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Datum rodjenja</p>
                    </td>
                    <td>

                    </td>
                </tr>
                <td>
                    <p>Korisnicko ime</p>
                </td>
                <td>
                    <input id="txtUsername" type="text" v-model = "user.username">
                </td>
                </tr>
                <tr>
                    <td>
                        <p>Lozinka</p>
                    </td>
                    <td>
                        <input class="txtPassword" type="text" v-model = "user.password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Ponovo lozinka</p>
                    </td>
                    <td>
                        <input class="txtPassword" type="text">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Potvrdi" @click='registerUser'>
                    </td>
                </tr>
            </table>
        </form>
        
	
`
	, 
	methods : {
		registerUser : function () {
			event.preventDefault();
			axios.post('/rest/user/registration', this.user).
			then(response => {
                router.push(`/`); 
            }).catch(err=> {
                   console.log(err.data)
                });
		}
	}
});
// <input id="txtDateOfBirth" type="date" v-model = "user.dateOfBirth">