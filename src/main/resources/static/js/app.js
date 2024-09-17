$(document).ready(function() {
    const apiUrl = '/api/users';

    function fetchUsers() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                const userTableBody = $('#userTableBody');
                userTableBody.empty();
                data.forEach(user => {
                    userTableBody.append(`
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>
                                <button class="btn btn-warning btn-sm edit-btn" data-id="${user.id}">Edit</button>
                                <button class="btn btn-danger btn-sm delete-btn" data-id="${user.id}">Delete</button>
                            </td>
                        </tr>
                    `);
                });
            })
            .catch(error => console.error('Error fetching users:', error));
    }

    fetchUsers();

    $('#userForm').submit(function(event) {
        event.preventDefault();
        const userId = $('#userId').val();
        const userName = $('#userName').val();
        const userEmail = $('#userEmail').val();
        const user = { name: userName, email: userEmail };

        const method = userId ? 'PUT' : 'POST';
        const url = userId ? `${apiUrl}/${userId}` : apiUrl;

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(response => response.json())
            .then(data => {
                $('#userModal').modal('hide');
                fetchUsers();
            })
            .catch(error => console.error('Error saving user:', error));
    });

    $(document).on('click', '.edit-btn', function() {
        const userId = $(this).data('id');
        fetch(`${apiUrl}/${userId}`)
            .then(response => response.json())
            .then(user => {
                $('#userId').val(user.id);
                $('#userName').val(user.name);
                $('#userEmail').val(user.email);
                $('#userModal').modal('show');
            })
            .catch(error => console.error('Error fetching user:', error));
    });

    $(document).on('click', '.delete-btn', function() {
        const userId = $(this).data('id');
        if (confirm('Are you sure you want to delete this user?')) {
            fetch(`${apiUrl}/${userId}`, {
                method: 'DELETE'
            })
                .then(() => fetchUsers())
                .catch(error => console.error('Error deleting user:', error));
        }
    });

    $('#userModal').on('show.bs.modal', function(event) {
        if (!$(event.relatedTarget).hasClass('edit-btn')) {
            $('#userForm')[0].reset();
            $('#userId').val('');
        }
    });
});