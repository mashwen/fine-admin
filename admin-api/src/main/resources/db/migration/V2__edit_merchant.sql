ALTER TABLE `as_auth`.`p_merchant`
  ADD COLUMN `mer_id` VARCHAR(25) NULL AFTER `updated_at`,
  ADD COLUMN `acct_no` VARCHAR(45) NULL AFTER `mer_id`,
  ADD COLUMN `mer_info` TEXT NULL AFTER `acct_no`,
  ADD COLUMN `customer_type` CHAR(2) NULL AFTER `mer_info`,
  ADD COLUMN `bank_info` TEXT NULL AFTER `customer_type`,
  ADD COLUMN `ali_info` TEXT NULL AFTER `bank_info`,
  ADD COLUMN `ali_chl_code` VARCHAR(25) NULL AFTER `ali_info`,
  ADD COLUMN `ali_chl_mer_id` VARCHAR(25) NULL AFTER `ali_chl_code`,
  ADD COLUMN `wechat_info` TEXT NULL AFTER `ali_chl_mer_id`,
  ADD COLUMN `wechat_chl_code` VARCHAR(25) NULL AFTER `wechat_info`,
  ADD COLUMN `wechat_chl_mer_id` VARCHAR(25) NULL AFTER `wechat_chl_code`,
  ADD COLUMN `wechat_app_id` VARCHAR(25) NULL AFTER `wechat_chl_mer_id`,
  ADD COLUMN `wechat_path` VARCHAR(25) NULL AFTER `wechat_app_id`
;

