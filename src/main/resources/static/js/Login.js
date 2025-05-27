  document.addEventListener('DOMContentLoaded', () => {
     const form = document.getElementById('loginForm');
     if (!form) {
         console.error('Form element not found.');
         return;
     }
 
     form.addEventListener('submit', async (event) => {
         console.log('Form submit event triggered'); // Log when the event is triggered
         event.preventDefault(); // Prevent the default form submission
 
         const username = document.getElementById("Username").value;
         const password = document.getElementById("Password").value;
         
         // Combine variables into a single object
         const formData = {
             username: username,
             password: password,
         };
 
         try {
             const response = await fetch('/login-packet', {
                 method: 'POST',
                 headers: {
                     'Content-Type': 'application/json'
                 },
                 body: JSON.stringify(formData)
             });
 
             const data = await response.json();
 
             if (response.ok) {
                setCache(data.token);
                 window.top.location.href = data.redirectURL;
             } else {
                 alert('Login failed: ' + data.message);
             }
         } catch (error) {
             console.error('Error:', error);
             alert('Error submitting the form.');
         }
     });

      function setCache(data) {
        localStorage.setItem("token", data);
      }
 });