package crud.dao;

import crud.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String role) {
        Role roleName = entityManager.createQuery("select r from Role r where r.role = :role", Role.class)
                .setParameter("role",role )
                .getSingleResult();
        return roleName;
    }
}
