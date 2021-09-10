Vue.component("registration", {
    data: function () {
        return {
            id: -1,
            passwordAgain: '',
            user: {
                username: '',
                password: '',
                name: '',
                surname: '',
                //dateOfBirth:null,
                userRole: '',
                gender:'',
            }
        }
    },
    props:['restaurantId'],
    template: ` 
    <div><custom-header></custom-header>
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
                        <input type="radio" id="male" name="gender" value="male" @change="user.gender='male'">
                        <label for="male">Muski</label>
                        <input type="radio" id="female" name="gender" value="female" @change="user.gender='female'">
                        <label for="female">Zenski</label><br>
            
            </div>
                <p>Datum rodjenja</p>
                <input id="txtDateOfBirth" type="date" v-model = "user.dateOfBirthString">
            <div class="field">    
                <input id="txtUsername" placeholder="Korisnicko ime" type="text" v-model = "user.username">
            </div>
            <div class="field">    
                <input class="txtPassword" placeholder="Lozinka" type="text" v-model = "user.password">
            </div>
            <div class="field">
            <input class="txtPassword" placeholder="Ponovo lozinka"  v-model="passwordAgain" type="text">
            </div>
            <div class="field">
                <input type="submit" value="Potvrdi" @click="registerUser">
            </div>
            </form>
        </div>
        </div>
	
`
    ,
    methods: {
        registerUser: function (event) {
            event.preventDefault();
            if (this.passwordAgain !== this.user.password) {
                alert("Polja za lozinku nisu ista.")
                return;
            }
            this.user.dateOfBirthString = new Date(this.user.dateOfBirthString);
            let userRole=this.$route.params.role;
            if(this.restaurantId)
            {
                this.user.restaurantId=this.restaurantId; 
                userRole='managers'
            }
            axios.post('/rest/users/' + userRole, this.user).
                then(response => {
                    alert("uspijesna registracija");
                    router.push(`/`);
                }).catch(err => {
                    console.log(err.data)
                });
        }
    }
});
// <input id="txtDateOfBirth" type="date" v-model = "user.dateOfBirth">