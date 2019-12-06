package com.example.restapp.repo;

import com.example.restapp.model.ExRateSubscription;
import com.example.restapp.model.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<UserDetails,String> {
}
