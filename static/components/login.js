Vue.component("login", {
	data: function () {
		    return {
		      id : -1,
		      user: {username: '', password:''}
		    }
	},
	template: ` 
    <div>
    <custom-header></custom-header>
	<div id="loginForm">
    <p id="title">Prijava</p>
        <form>  
            <div class="field">
            <span></span>
                <input v-model = "user.username" name = "username" type="text" placeholder="Username">
            </div>    
            <div class="field">
            <span></span>    
                <input v-model = "user.password" name = "password" type="password" placeholder="Password">
            </div>
            <div class="field">       
                <input type = "submit" v-on:click = "loginUser" value = "LOGIN">
            </div>    
        <a href="#/registration/buyer">Registrujte se.</a>
        </form>	  
    </div>
    </div>
       `
	, 
	methods : {
		loginUser : function () {
			event.preventDefault();
			axios.post('/rest/users/login', this.user).
			then(response => {
                router.push('/profile');
            })
            .catch(function(error){
                alert(error.response.data,"Greska")
            })
            
		}
	}
});