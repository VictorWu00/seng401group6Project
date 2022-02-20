// https://stackoverflow.com/questions/12460378/how-to-get-json-from-url-in-javascript
var getJSON = function(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';
    xhr.onload = function() {
        var status = xhr.status;
        callback(status, xhr.response);
    };
    xhr.send();
};

function _showModal(job) {
    // shortcut to set element inner html w/o having to write long
    var s = (id, text) => { document.getElementById(id).innerHTML = text ? text : "-" };

    s("modalTitle", job.position)
    s("infoCompanyName", job.company.name);
    s("infoCompanyAddress", job.company.address);
    s("infoCompanyCity", job.company.city);
    s("infoCompanyEmail", job.company.hrEmail);
    s("infoCompanyPhone", job.company.phone);
    s("infoCompanySalary", "$" + job.company.minSalary + " - $" + job.company.maxSalary);
    s("infoRequiredStatus", job.jobRequirement.requiredGradStatus);
    s("infoRequiredGPA", job.jobRequirement.requiredGPA);
    s("infoRequiredAge", job.jobRequirement.requiredAge);

    // display
    document.getElementById("infoModal").classList.add("is-active")
}

function hideModal() {
    document.getElementById("infoModal").classList.remove("is-active")
}

function showDetailClicked(jobId) {
    var url = "/job/" + jobId;

    getJSON(url, (code, body) => {
        if (code == 200) {
            _showModal(body)
        }
    });
}
