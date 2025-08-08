package com.example.HospitalManagment.repository;

import com.example.HospitalManagment.data.user.UserView;
import com.example.HospitalManagment.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NativeQueryRepository {
    private final EntityManager entityManager;

    public NativeQueryRepository(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    public List<UserView> getUsers(Integer page, Integer size, String search) {
        page = page - 1;
        Integer offSet = size * page;

        String queryString = "SELECT " +
                "t.id, t.created_at, t.created_by, t.first_name, t.last_name, t.email, " +
                "t.phone_number, t.status, r.name AS roleName " +
                "FROM users t " +
                "LEFT JOIN roles r ON t.role_id = r.id " +
                "WHERE t.deleted_at IS NULL " +
                "GROUP BY t.id ";

        if (search != null && !search.isEmpty()) {
            queryString += "HAVING (t.first_name LIKE '%" + search + "%' OR t.last_name LIKE '%" + search + "%' OR t.email LIKE '%" + search + "%') ";
        }

        queryString += "ORDER BY t.id DESC LIMIT " + size + " OFFSET " + offSet;

        Query query = entityManager.createNativeQuery(queryString);

        List<Object[]> resultSet = query.getResultList();

        return getUserList(resultSet);
    }
    public long getUsersCount(String search) {
        String countQueryString = "SELECT COUNT(DISTINCT t.id) FROM users t " +

                "WHERE t.deleted_at IS NULL ";

        if (search != null && !search.isEmpty()) {
            countQueryString += "AND (t.first_name LIKE '%" + search + "%' OR t.last_name LIKE '%" + search + "%' OR t.email LIKE '%" + search + "%')";
        }

        Query countQuery = entityManager.createNativeQuery(countQueryString);
        return ((Number) countQuery.getSingleResult()).longValue();
    }


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    private List<UserView> getUserList(List<Object[]> resultSet) {
        List<UserView> userViews = new ArrayList<>();

        for (Object[] objects : resultSet) {
            UserView userView = new UserView();

            if (objects[0] != null) {
                userView.setId(Long.valueOf(String.valueOf(objects[0])));
            }
            if (objects[1] != null) {
                userView.setCreatedAt(LocalDateTime.parse(String.valueOf(objects[1]), formatter));
            }
            if (objects[2] != null) {
                userView.setCreatedBy(String.valueOf(objects[2]));
            }
            if (objects[3] != null) {
                userView.setFirstName(String.valueOf(objects[3]));
            }
            if (objects[4] != null) {
                userView.setLastName(String.valueOf(objects[4]));
            }
            if (objects[5] != null) {
                userView.setEmail(String.valueOf(objects[5]));
            }
            if (objects[6] != null) {
                userView.setPhoneNumber(String.valueOf(objects[6]));
            }
            if (objects[7] != null) {
                userView.setStatus(Status.valueOf(String.valueOf(objects[7])));
            }
            if (objects[8] != null) {
                userView.setRole(String.valueOf(objects[8]));
            }

            userViews.add(userView);
        }

        return userViews;
    }




}