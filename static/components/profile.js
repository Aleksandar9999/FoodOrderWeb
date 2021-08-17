Vue.component("profile", {
    data: function () {
        return {
            user: null,
            username:'',
        }
    },
    template: ` 
	<div class="personalInfo">
            <div class="hederInfo">
                <div style="display: inline-block;">
                    <h2>Osnovne informacije</h2>
                    <div style="margin-top: 18px;">
                        <p>{{user.name}} {{user.surname}}</p>
                        <p>{{user.userRole}}</p>
                    </div>
                </div>
                <div style="display: inline-block;">
                    <div style="margin-top: 18px;">
                        <p>{{user.pointsCollected}}</p>
                        <p>TIP</p>
                    </div>
                </div>
            </div>
            <div class="hederInfo" style="margin-top: 10px;">
                <form>
                    <table style="border-spacing: 10px;
                border-collapse: separate;">
                        <tr>
                            <td style=" padding: 10px 30px 10px 0px; ">
                                <p>Ime</p>
                                <div class="field">
                                    <input type="text"  v-model=user.name>
                                </div>
                            </td>
                            <td>
                                <p>Prezime</p>
                                <div class="field">
                                    <input type="text"  v-model=user.surname>
                                </div>
                            </td>
                        </tr>
                        <tr style="padding-top: 10px;">
                            <td style=" padding: 10px 30px 10px 0px; ">
                                <p>Korisnicko ime</p>
                                <div class="field">
                                    <input type="text" v-model=user.username>
                                </div>
                            </td>
                            <td>
                                <p>Nova lozinak</p>
                                <div class="field">
                                    <input type="password" id="name">
                                </div>
                            </td>
                        </tr>
                        <tr style="padding-top: 10px;">
                            <td>
                            </td>
                            <td>
                                <div class="field">
                                    <input type="submit" value="Potvrdi" @click=updateUser>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
`
    ,
    mounted() {
        if (this.$route.params.id) {
            axios
                .get('rest/users/' + this.$route.params.id)
                .then(response => {
                    this.user = response.data;
                })
        } else
            axios
                .get('rest/users/me')
                .then(response => {
                    this.user = response.data;
                    this.username=response.data.username;
                })
    },
    methods: {
        updateUser: function () {
            axios.put('/rest/users/' + this.username, this.user).
                then(response => (alert("uspjesno")))
        }
    }
});
