-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    VALUES ('1', '${table.comment}', '${package.ModuleName}/${cfg.entityName}', NULL, '1', 'config', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '查看', null, '${package.ModuleName}:${cfg.entityName}:list,${package.ModuleName}:${cfg.entityName}:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '新增', null, '${package.ModuleName}:${cfg.entityName}:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '修改', null, '${package.ModuleName}:${cfg.entityName}:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '删除', null, '${package.ModuleName}:${cfg.entityName}:delete', '2', null, '6';
