package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.Potluck;
import com.buildweek.unit4javabuild.repository.PotluckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "potluckService")
public class PotluckServicesImpl implements PotluckServices
{
    @Autowired
    private PotluckRepository potluckrepo;

    @Override
    public List<Potluck> findAll() {
        List<Potluck> myList = new ArrayList<>();

        potluckrepo.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }

    @Override
    public Potluck findPotluckById(long id) throws Exception {
        return potluckrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Potluck id " + id + " not Found!"));
    }

    @Override
    public List<Potluck> findByNameContaining(String name)
    {
        return potluckrepo.findByNameContainingIgnoreCase(name.toLowerCase());
    }

    @Override
    public Potluck save(Potluck potluck) {
        return null;
    }

    @Override
    public Potluck update(Potluck potluck, long id) {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        potluckrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Potluck id " + id + " not found!"));
        potluckrepo.deleteById(id);
    }
}
