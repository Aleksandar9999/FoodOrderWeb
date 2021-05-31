Vue.component("login", {
	data: function () {
		    return {
		      id : -1,
		      user: {username: '', password:''}
		    }
	},
	template: ` 
    <div id="header">
    <h2 style="display: inline;">FoodOrder</h2>
    <input style="display: inline; margin-left: 10%;" type="button" value="PROFILE">
    </div>
`
	, 
	methods : {
		loginUser : function () {
			event.preventDefault();
			axios.post('/rest/user/login', this.user).
			then(response => (router.push(`/profile`)));
		}
	}
});