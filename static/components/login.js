Vue.component("login", {
	data: function () {
		    return {
		      id : -1,
		      user: {username: '', password:''}
		    }
	},
	template: ` 
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
        <a href="#/registration">Registrujte se.</a>
        </form>	  
    </div>
       `
	, 
	methods : {
		loginUser : function () {
			event.preventDefault();
			axios.post('/rest/user/login', this.user).
			then(response => {
                if(response.data['userRole']=="Administrator")
                    router.push('/administrator');
                else if(response.data['userRole']=="Manager")
                    router.push('/restaurant/'+response.data['restaurantId']);
                else router.push('/profile');
                
            })
            .catch(function(error){
                alert(error.response.data,"Greska")
            })
            
		}
	}
});