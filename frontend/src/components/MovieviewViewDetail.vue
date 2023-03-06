<template>

    <v-card outlined>
        <v-card-title>
            Movieview # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="MovieId" v-model="item.movieId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Desc" v-model="item.desc" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="ReviewCnt" v-model="item.reviewCnt" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="MovieStatus" v-model="item.movieStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="RsvId" v-model="item.rsvId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="RsvStatus" v-model="item.rsvStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="CustomerId" v-model="item.customerId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="NumberOfSeats" v-model="item.numberOfSeats" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'MovieviewViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/movieviews/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

