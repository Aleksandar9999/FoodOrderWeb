Vue.component("registration-manager", {
	data: function () {
		    return {
		      id : -1,
		      user: {
                 username:'',
                 password:'',
                 name:'',
                 surname:'',
                 restaurantId: this.$route.params.id,
                 //dateOfBirth:null,
                 userRole:"Manager"
              }
		    }
	},
	template: ` 
	<registration :restaurantId='user.restaurantId'></registration>
`
	, 
	methods : {
		registerUser : function () {
			axios.post('/rest/users/managers', this.user).
			then(response => {
                router.push(`/`); 
            }).catch(err=> {
                   console.log(err.data)
                });
		}
	}
});