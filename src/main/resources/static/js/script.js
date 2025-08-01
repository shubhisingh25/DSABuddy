/**
 * 
 */// static/js/script.js
function toggleRevised(problemId, button) {
    // Toggle logic (can integrate via AJAX or reload)
    button.classList.toggle("btn-success");
    button.classList.toggle("btn-secondary");
    // Backend update left as extension
}
themeToggle.addEventListener('click', function() {
    document.body.classList.toggle('dark-mode');
    themeToggle.innerHTML = document.body.classList.contains('dark-mode') ? 
      '<i class="fas fa-sun"></i>' : '<i class="fas fa-moon"></i>';
  });

$(document).ready(function() {
    // Handle form submissions with AJAX
    $('form').submit(function(e) {
        e.preventDefault();
        var form = $(this);
        
        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: form.serialize(),
            success: function() {
                // Reload just the table section
                $('#problem-table').load(window.location.href + ' #problem-table > *');
            },
            error: function(xhr) {
                alert('Error: ' + xhr.responseText);
            }
        });
    });
});