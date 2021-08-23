var map;
var restaurant_location;
function simpleReverseGeocoding(lon, lat) {
    axios.get('http://nominatim.openstreetmap.org/reverse?format=json&lon=' + lon + '&lat=' + lat)
    .then(response=>{
      restaurant_location.address.city=response.data.address.city;
      restaurant_location.address.number=response.data.address.house_number;
      restaurant_location.address.street=response.data.address.road;
      restaurant_location.address.zipCode=response.data.address.postcode;
      restaurant_location.latitude=lat;
      restaurant_location.longitude=lon;
      return response.data;
    })
};
function addMarker(lat,long){
  var markers = new ol.layer.Vector({
    source: new ol.source.Vector(),
    style: new ol.style.Style({
      image: new ol.style.Icon({
        anchor: [0.5, 1],
        src: '../files/images/marker.png',
        innerHeight:20,
        innerWidth:50
      })
    })
  });
  map.addLayer(markers);
  
  var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([long, lat])));
  markers.getSource().addFeature(marker);
};

  /*document.getElementById('reversegeocoding').addEventListener('click', function(e) {
    if (document.getElementById('lon').value && document.getElementById('lat').value) {
      simpleReverseGeocoding(document.getElementById('lon').value, document.getElementById('lat').value);
    }
  });*/