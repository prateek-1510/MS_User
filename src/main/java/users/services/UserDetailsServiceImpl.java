package users.services;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import users.model.Address;
import users.model.User;

import java.util.List;

@Service
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    public List<User> fetchUsers()
    {
        return fetchUsersList();
    }

    private List<User> fetchUsersList()
    {
        Address address=new Address();
        address.setAddressLine1("4/1045, vikas nagar");
        address.setAddressLine2("Lucknow");

        User user=new User();
        user.setAddress(address);
        user.setName("prateek");

        return List.of(user);
    }



}
