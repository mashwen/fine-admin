ALTER TABLE `as_auth`.`p_merchant`

  ADD COLUMN `up_info` TEXT NULL AFTER `wechat_app_id`,
  ADD COLUMN `up_chl_code` VARCHAR(25) NULL AFTER `up_info`,
  ADD COLUMN `up_chl_mer_id` VARCHAR(25) NULL AFTER `up_chl_code`

;

