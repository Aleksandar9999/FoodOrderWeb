Vue.component("login", {
	data: function () {
		    return {
		      id : -1,
		      user: {username: '', password:''}
		    }
	},
	template: ` 
	
	<form>
            <table>
                <tr>
                    <td>
                        <input v-model = "user.username" name = "username" type="text" placeholder="Username">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input v-model = "user.password" name = "password" type="password" placeholder="Password">
                    </td>
                </tr>
                <tr>
                    <td>
						<input type = "submit" v-on:click = "loginUser" value = "LOGIN">
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/registration">Registrujte se.</a>
                    </td>
                </tr>
            </table>
       </form>	  
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
                
            } );
		}
	}
});