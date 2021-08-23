Vue.component("restaurant-map", {
  data: function () {
    return {
      map: null,
    }
  },
  props: ['location'],
  template: ` 
	<div ref="map-root" id="map-root"> </div>
    `
  ,
  mounted() {
    this.map = new ol.Map({
      target: this.$refs['map-root'], layers: [new ol.layer.Tile({ source: new ol.source.OSM() }),],
      view: new ol.View({
        zoom: 2,
        center: [0, 0],
      })
    })
    map = this.map;
    restaurant_location=this.location;    
    if (this.location.latitude) {
      addMarker(this.location.latitude, this.location.longitude)
      this.map.getView().setCenter(ol.proj.transform([this.location.longitude, this.location.latitude], 'EPSG:4326', 'EPSG:3857'));
      this.map.getView().setZoom(5);
    }

    this.map.on('click', function (e) {
      var coordinate = ol.proj.toLonLat(e.coordinate).map(function (val) {
        return val.toFixed(6);
      });
      lon = coordinate[0];
      lat = coordinate[1];
      addMarker(lat, lon);
      simpleReverseGeocoding(lon, lat);
    });
  },
  methods: {
    addMarker(lat, long) {
      var markers = new ol.layer.Vector({
        source: new ol.source.Vector(),
        style: new ol.style.Style({
          image: new ol.style.Icon({
            anchor: [0.5, 1],
            src: '../files/images/marker.png',
            innerHeight: 20,
            innerWidth: 50
          })
        })
      });
      this.map.addLayer(markers);

      var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([long, lat])));
      markers.getSource().addFeature(marker);
    },
  }
});
