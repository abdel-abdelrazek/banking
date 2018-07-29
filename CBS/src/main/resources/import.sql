INSERT INTO `authority` (`id`, `authority`, `username`, `credentials_id`) VALUES(1, 'ROLE_USER', 'guest', NULL);
INSERT INTO `authority` (`id`, `authority`, `username`, `credentials_id`) VALUES(2, 'ROLE_ADMIN', 'admin', NULL);
INSERT INTO `authority` (`id`, `authority`, `username`, `credentials_id`) VALUES(3, 'ROLE_USER', 'admin', NULL);
INSERT INTO `credentials` (`username`, `enabled`, `password`, `verifyPassword`) VALUES('guest', b'1', '$2a$10$0.ESlGysrPaiW5HaapKwoehzWt5AibgbPPOvMhDv8D6H26QQ/CwhS', NULL);
INSERT INTO `credentials` (`username`, `enabled`, `password`, `verifyPassword`) VALUES('admin', b'1', '$2a$10$S/wlXEo/APzf.Sn1cO2p4.V12EJmaw.uzrHelMvkpuahjmHWnSafe', NULL);
INSERT INTO `member` (`id`, `age`, `firstName`, `lastName`, `memberNumber`, `title`, `member_id`) VALUES (1, 12, 'Curious', 'George', 8754, 'Boy Monkey', 'admin');
INSERT INTO `member` (`id`, `age`, `firstName`, `lastName`, `memberNumber`, `title`, `member_id`) VALUES (2, 123, 'Allen', 'Rench', 8733, 'Torque Master', 'guest');

NSERT INTO `transaction` VALUES (1,'credit','\0',123,'2018-05-23 17:44:45','cr',1),
(2,'debit','\0',122,'2018-05-23 17:44:52','dr',1),
(3,'credit','\0',123,'2018-05-24 00:16:07','cr',2),
(4,'debit','\0',32,'2018-05-24 00:16:16','dr',2);