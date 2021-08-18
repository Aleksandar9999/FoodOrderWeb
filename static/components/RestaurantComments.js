Vue.component("restaurant-comments", {
    data: function () {
        return {
            comments: null,
            userRole: null,

        }
    },
    props: ['type'],
    template: ` 
     <div class="hederInfo" style="margin-top: 10px;">
        <table id="userList" border="0" CELLSPACING=0>
            <tr v-for="(p) in comments" >
                <td>
                    <p>{{p.comment}}</p>
                    <p>{{p.mark}}</p>
                </td>
                <td>
                    <p>{{p.buyerUsername}}</p>
                </td>
                <td v-if="type !=='' && p.status==='Pending'">
                    <button @click="submit(p,'Confirmed')">Potvrdi</button>
                    <button @click="submit(p,'Rejected')">Otkazi</button>
                </td>
                
                </tr>
		        </table>
            </div>
`
    ,
    mounted() {
        let route='/rest/restaurants/' + this.$route.params.id + '/comments';
        if(this.type) route += '/' + this.type
        axios.get(route).then(response => { this.comments = response.data; console.log(this.comments)})
    }, 
    methods:{
        submit(comment, status){
            comment.status=status;
            axios.put('/rest/comments/'+comment.id,comment).then(response=>{
                alert(status);
            })
        }
    }
});
