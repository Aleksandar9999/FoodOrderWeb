Vue.component("add-new-article", {
    
    props: ['show','actionButton','dynamicTitle','articleOperation','article'],
    template: `
    <div v-if="this.show">
    <transition name="model">
     <div class="modal-mask">
      <div class="modal-wrapper">
       <div class="modal-dialog" style="color: black;" >
        <div class="modal-content" style="background-color: white; width: 80%; margin: 0 auto;">
            <div class="modal-header">
                <button type="button" class="close" @click="show=false"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >{{dynamicTitle}}</h4>
            </div>
            
            <div class="modal-body">
                <div class="form-group">
                 <label>Naziv</label> 
                 <input type="text" class="form-control" v-model="article.name"/>
                </div>
                <div class="form-group">
                 <label>Cena</label>
                 <input type="text" class="form-control" v-model="article.price" />
                </div>
                <div class="form-group">
                 <label>Tip</label>
                    <select v-model="article.articleType"  style="height: 30px;">
                        <option value=""></option>
                        <option value="Food">Hrana</option>
                        <option value="Drink">Piće</option>
                    </select>
                </div>
                <div class="form-group">
                 <label>Slika</label>
                 <input type="file" class="form-control" @change='addImage'/>
                </div>
                <div class="form-group">
                 <label>Opis</label>
                 <input type="text" class="form-control" v-model="article.comment"/>
                </div><div class="form-group">
                <label>Kolicina</label>
                <input type="text" class="form-control" v-model="article.amount"/>
               </div>
                <br />
                <div>
                 <input type="hidden"  />
                 <input type="button" class="btn btn-success btn-xs" v-model="actionButton" @click="submitData" />
                </div>
               </div>
        </div>
       </div>
      </div>
     </div>
    </transition>
   </div>
    `,
    methods:{
        addImage(event){
            const formData = new FormData();
            formData.append("logo", event.target.files[0]);
            formData.append("id", 7878);
            axios.post("/rest/upload", formData)
                .then(function (result) {
                    console.log(result);
                }, function (error) {
                    console.log(error);
                });
            this.article.imageUrl = event.target.files[0].name;
        },
        submitData() {
            if (this.articleOperation === 'Create') {
                axios
                    .post('rest/restaurants/' + this.$route.params.id + '/articles', this.article)
                    .then(response => {
                        this.restaurant = response.data;
                        axios
                            .get('rest/restaurants/' + this.$route.params.id + '/articles')
                            .then(response => {
                                this.articles = response.data;
                                console.log("getovvao")
                            })
                        alert("Uspijesno ste dodali artikal");
                    })
                    .catch(function (error) {
                        alert(error.response.data, "Greska")
                    })
            }
            else {
                axios
                    .put('rest/restaurants/' + this.$route.params.id + '/articles/' + this.article.id, this.article)
                    .then(response => {
                        this.restaurant = response.data;
                        alert("Success.")
                    })
                    .catch(function (error) {
                        alert(error.response.data, "Greska")
                    })
            }


        }
    }
});