DELETE FROM USERS;
DELETE FROM ROLES;

INSERT INTO ROLES (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLES (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO users(
            id, email, enabled, firstname, last_password_reset_date, lastname, 
            "password", username, dock_id, role_id, warehouse_id)
    VALUES (nextval('users_id_seq'), 'wael.trabelsi@wevioo.com', TRUE, 'admin', now(), 'admin', '$2a$04$NV2YrsczQaXUpgmBWTmlVeSHE8I.S6vCPyIMrkws3cWCnK5W8/CYW', 
            'admin', null, 1, null);
