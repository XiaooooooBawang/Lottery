create database lottery_02;

-- auto-generated definition
create table user_take_activity
(
    id            bigint                             null,
    u_id          tinytext                           null,
    take_id       bigint                             null,
    activity_id   bigint                             null,
    activity_name tinytext                           null,
    take_date     timestamp                          null,
    take_count    int                                null,
    uuid          tinytext                           null,
    create_time   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP not null comment '修改时间'
)
    comment '用户参与活动记录表';

-- auto-generated definition
create table user_take_activity_count
(
    id          bigint                             null,
    u_id        tinytext                           null,
    activity_id bigint                             null,
    total_count int                                null,
    left_count  int                                null,
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '修改时间'
)
    comment '用户活动参与次数表';

-- auto-generated definition
create table user_strategy_export_001
(
    id            bigint                             null,
    u_id          mediumtext                         null,
    activity_id   bigint                             null,
    order_id      bigint                             null,
    strategy_id   bigint                             null,
    strategy_type int                                null,
    grant_type    int                                null,
    grant_date    timestamp                          null,
    grant_state   int                                null,
    award_id      bigint                             null,
    award_type    int                                null,
    award_name    mediumtext                         null,
    award_content mediumtext                         null,
    uuid          mediumtext                         null,
    create_time   datetime default CURRENT_TIMESTAMP not null,
    update_time   datetime default CURRENT_TIMESTAMP not null
) comment '用户策略计算结果表';
create table user_strategy_export_002
(
    id            bigint                             null,
    u_id          mediumtext                         null,
    activity_id   bigint                             null,
    order_id      bigint                             null,
    strategy_id   bigint                             null,
    strategy_type int                                null,
    grant_type    int                                null,
    grant_date    timestamp                          null,
    grant_state   int                                null,
    award_id      bigint                             null,
    award_type    int                                null,
    award_name    mediumtext                         null,
    award_content mediumtext                         null,
    uuid          mediumtext                         null,
    create_time   datetime default CURRENT_TIMESTAMP not null,
    update_time   datetime default CURRENT_TIMESTAMP not null
) comment '用户策略计算结果表';
create table user_strategy_export_003
(
    id            bigint                             null,
    u_id          mediumtext                         null,
    activity_id   bigint                             null,
    order_id      bigint                             null,
    strategy_id   bigint                             null,
    strategy_type int                                null,
    grant_type    int                                null,
    grant_date    timestamp                          null,
    grant_state   int                                null,
    award_id      bigint                             null,
    award_type    int                                null,
    award_name    mediumtext                         null,
    award_content mediumtext                         null,
    uuid          mediumtext                         null,
    create_time   datetime default CURRENT_TIMESTAMP not null,
    update_time   datetime default CURRENT_TIMESTAMP not null
) comment '用户策略计算结果表';
create table user_strategy_export_004
(
    id            bigint                             null,
    u_id          mediumtext                         null,
    activity_id   bigint                             null,
    order_id      bigint                             null,
    strategy_id   bigint                             null,
    strategy_type int                                null,
    grant_type    int                                null,
    grant_date    timestamp                          null,
    grant_state   int                                null,
    award_id      bigint                             null,
    award_type    int                                null,
    award_name    mediumtext                         null,
    award_content mediumtext                         null,
    uuid          mediumtext                         null,
    create_time   datetime default CURRENT_TIMESTAMP not null,
    update_time   datetime default CURRENT_TIMESTAMP not null
) comment '用户策略计算结果表';
