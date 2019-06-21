ALTER TABLE `as_auth`.`p_user`

  ADD COLUMN `nick_name` VARCHAR(30) NULL  AFTER `default_qrcode`,
  ADD COLUMN `sex` TINYINT(2) NULL AFTER `nick_name`,
  ADD COLUMN `avatar` MEDIUMBLOB NULL AFTER `sex`

;

