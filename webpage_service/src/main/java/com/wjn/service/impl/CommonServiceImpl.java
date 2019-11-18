package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wjn.bean.dto.BannerDto;
import com.wjn.bean.dto.HeaderDto;
import com.wjn.bean.dto.MenuDto;
import com.wjn.mapper.BannerMapper;
import com.wjn.mapper.HeaderMapper;
import com.wjn.mapper.MenuMapper;
import com.wjn.model.admin.Banner;
import com.wjn.model.admin.BeanConstant;
import com.wjn.model.admin.Header;
import com.wjn.service.CommonService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.wjn.constant.GlobalConstant.TRUE;


/**
 * @auther WJN
 * @date 2019/9/30 16:49
 * @describetion
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private HeaderMapper headerMapper;

    /**
     * 通过查询数据库获取目录名称和目录的网址，然后封装给MenuDto，再添加进集合返回
     * @return list
     */
    @Override
    public List<MenuDto> getMenu() {
        List<Map<String,Object>> list =  menuMapper.getMenu();
        List<MenuDto> menuDtos = new LinkedList<>();
        for (Map<String, Object> map : list) {
            MenuDto menuDto = MenuDto.of()
                    .setMenuName(map.get(BeanConstant.MENUS).toString())
                    .setUrl(map.get(BeanConstant.URL).toString());
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }

    /**
     * 通过通用Mapper查询，查询条件未state为1的主题，因为启用的主题只能有一个，故直接使用headers.get(0)
     * 再把Header赋值给HeaderDto，返回。
     * @return HeaderDto
     */
    @Override
    public HeaderDto getHeader() {
        Example example = new Example(Header.class);
        example.createCriteria().andEqualTo(BeanConstant.STATE,TRUE);
        List<Header> headers = headerMapper.selectByExample(example);
        HeaderDto headerDto = new HeaderDto();
        BeanUtil.copyProperties(headers.get(0), headerDto);
        return headerDto;
    }

    @Override
    public BannerDto getBanner(String html) {

        Example example = new Example(Banner.class);
        example.createCriteria().andEqualTo("html",html);
        List<Banner> banners = bannerMapper.selectByExample(example);
        BannerDto bannerDto = BannerDto.of();
        BeanUtil.copyProperties(banners.get(0),bannerDto);
        return bannerDto;
    }

}
