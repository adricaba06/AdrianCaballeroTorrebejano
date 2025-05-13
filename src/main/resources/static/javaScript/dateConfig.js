    document.addEventListener("DOMContentLoaded", function () {
      const fechaInicio = document.getElementById("fechaInicio");
      const fechaFin = document.getElementById("fechaFin");

      if (fechaInicio.value) {
        fechaFin.min = fechaInicio.value;
      }

      fechaInicio.addEventListener("change", function () {
        fechaFin.min = this.value;
        if (fechaFin.value < this.value) {
          fechaFin.value = "";
        }
      });
    });