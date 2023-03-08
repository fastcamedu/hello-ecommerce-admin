const salesMetricUrl = "http://localhost:8080/api/sales-per-categories";
$(function () {
    $.ajax({
        dataType: "json",
        url: salesMetricUrl,
    }).done(function (data) {
        console.log("data.categories = " + data.categories);
        console.log("data.sales = " + data.sales);
        myPieChart.data.labels = data.categories;
        myPieChart.data.datasets[0].data = data.sales;
        myPieChart.update();
    });
});

